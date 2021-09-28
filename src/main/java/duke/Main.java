package duke;

import java.io.IOException;

import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for duke.Duke using FXML.
 */
public class Main extends Application {

    private final Chatbot duke = new Chatbot();

    /**
     * Starts the application.
     *
     * @param stage the stage of the application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
