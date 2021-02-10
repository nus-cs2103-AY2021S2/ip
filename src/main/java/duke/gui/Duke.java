package duke.gui;

import java.io.IOException;

import duke.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for duke.gui.Duke using FXML.
 */
public class Duke extends Application {

    private static Stage stage;
    private final Controller controller = new Controller();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            this.stage = stage;
            stage.setOnCloseRequest(e -> handleExit());
            stage.setTitle("duke.gui.Duke");
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setController(controller);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Quits the Duke GUI application.
     */
    public static void handleExit() {
        stage.close();
    }
}
