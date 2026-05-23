import javafx.fxml.FXML;

public class HomeController {
    @FXML
    private void openRecords() {
        try {
            MainApp.setRoot("records.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
