import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
//@@author JulietTeoh
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications.
public class Main extends Application {
    private static final String FILE_NAME = "dukedata.txt";
    private static final String FOLDER_NAME = "data";
    private static final String RELATIVE_PATH = FOLDER_NAME + "/" + FILE_NAME;
    private Duke duke = new Duke(RELATIVE_PATH, FOLDER_NAME);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
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
