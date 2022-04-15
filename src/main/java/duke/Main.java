package duke;

import java.io.IOException;

import duke.exception.DukeStorageException;
import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke;

    /**
     * Initializes and starts the application.
     *
     * @param stage Top level JavaFx container.
     */
    @Override
    public void start(Stage stage) {
        try {
            duke = new Duke();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Duke ChatBot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeStorageException e) {
            e.printStackTrace();
        }
    }
}
