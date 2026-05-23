import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ScholarController {
    @FXML private TextField txtFullName;
    @FXML private TextField txtProgram;
    @FXML private ChoiceBox<YearLevel> cbYearLevel;
    @FXML private ChoiceBox<ScholarStatus> cbStatus;

    @FXML private TableView<Scholar> table;
    @FXML private TableColumn<Scholar, Integer> colId;
    @FXML private TableColumn<Scholar, String> colFullName;
    @FXML private TableColumn<Scholar, String> colProgram;
    @FXML private TableColumn<Scholar, String> colYearLevel;
    @FXML private TableColumn<Scholar, String> colStatus;

    private ObservableList<Scholar> scholars = FXCollections.observableArrayList();
    private Connection conn;
    private int selectedId = -1;

    @FXML
    public void initialize() {
        conn = DatabaseConnection.connect();

        cbYearLevel.getItems().setAll(YearLevel.values());
        cbStatus.getItems().setAll(ScholarStatus.values());

        colId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colFullName.setCellValueFactory(data -> data.getValue().fullNameProperty());
        colProgram.setCellValueFactory(data -> data.getValue().programProperty());
        colYearLevel.setCellValueFactory(data -> data.getValue().yearLevelProperty());
        colStatus.setCellValueFactory(data -> data.getValue().statusProperty());

        table.setOnMouseClicked(event -> fillFormFromSelection());
        loadData();
    }

    private void loadData() {
        scholars.clear();

        if (conn == null) {
            showAlert("Database connection failed. Check your .env file and PostgreSQL setup.");
            return;
        }

        try {
            String query = "SELECT * FROM scholars ORDER BY id";
            ResultSet rs = conn.createStatement().executeQuery(query);

            while (rs.next()) {
                scholars.add(new Scholar(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("program"),
                        rs.getString("year_level"),
                        rs.getString("status")
                ));
            }

            table.setItems(scholars);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error loading records.");
        }
    }

    @FXML
    private void addScholar() {
        if (!isValidInput()) {
            return;
        }

        try {
            String query = "INSERT INTO scholars(full_name, program, year_level, status) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, txtFullName.getText().trim());
            pst.setString(2, txtProgram.getText().trim());
            pst.setString(3, cbYearLevel.getValue().toString());
            pst.setString(4, cbStatus.getValue().toString());
            pst.executeUpdate();

            loadData();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error adding record.");
        }
    }

    @FXML
    private void updateScholar() {
        if (selectedId == -1) {
            showAlert("Please select a record to update.");
            return;
        }

        if (!isValidInput()) {
            return;
        }

        try {
            String query = "UPDATE scholars SET full_name=?, program=?, year_level=?, status=? WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, txtFullName.getText().trim());
            pst.setString(2, txtProgram.getText().trim());
            pst.setString(3, cbYearLevel.getValue().toString());
            pst.setString(4, cbStatus.getValue().toString());
            pst.setInt(5, selectedId);
            pst.executeUpdate();

            loadData();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error updating record.");
        }
    }

    @FXML
    private void deleteScholar() {
        if (selectedId == -1) {
            showAlert("Please select a record to delete.");
            return;
        }

        try {
            String query = "DELETE FROM scholars WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setInt(1, selectedId);
            pst.executeUpdate();

            loadData();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error deleting record.");
        }
    }

    @FXML
    private void clearFields() {
        txtFullName.clear();
        txtProgram.clear();
        cbYearLevel.setValue(null);
        cbStatus.setValue(null);
        table.getSelectionModel().clearSelection();
        selectedId = -1;
    }

    @FXML
    private void goHome() {
        try {
            MainApp.setRoot("home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillFormFromSelection() {
        Scholar scholar = table.getSelectionModel().getSelectedItem();

        if (scholar == null) {
            return;
        }

        selectedId = scholar.getId();
        txtFullName.setText(scholar.getFullName());
        txtProgram.setText(scholar.getProgram());
        cbYearLevel.setValue(findYearLevel(scholar.getYearLevel()));
        cbStatus.setValue(findStatus(scholar.getStatus()));
    }

    private boolean isValidInput() {
        if (txtFullName.getText().isBlank()
                || txtProgram.getText().isBlank()
                || cbYearLevel.getValue() == null
                || cbStatus.getValue() == null) {
            showAlert("Please complete all fields.");
            return false;
        }

        return true;
    }

    private YearLevel findYearLevel(String value) {
        for (YearLevel yearLevel : YearLevel.values()) {
            if (yearLevel.toString().equals(value)) {
                return yearLevel;
            }
        }

        return null;
    }

    private ScholarStatus findStatus(String value) {
        for (ScholarStatus status : ScholarStatus.values()) {
            if (status.toString().equals(value)) {
                return status;
            }
        }

        return null;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Campus Scholar Manager");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
