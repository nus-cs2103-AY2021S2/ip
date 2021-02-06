import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import duke.Duke;

public class Main extends Application {
    /** Constant to store the database path for duke.Duke's commands */
    private static final String DATABASE_FILE_PATH = "data/duke.txt";
    /** Constant storing database directory path */
    private static final String DATABASE_DIRECTORY_PATH = "data/";

    /** Create private instance of Duke on init */
    private Duke duke = new Duke(DATABASE_FILE_PATH, DATABASE_DIRECTORY_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
