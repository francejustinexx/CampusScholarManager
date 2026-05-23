import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        Scene scene = new Scene(loadFXML("home.fxml"), 760, 540);

        stage.setTitle("Campus Scholar Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws Exception {
        mainStage.getScene().setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(fxml));
        return loader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
