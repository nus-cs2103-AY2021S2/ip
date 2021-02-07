package duke.gui;

import duke.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for duke.gui.Duke using FXML.
 */
public class Duke extends Application {

    private Controller controller = new Controller();
    private static Stage stage;

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