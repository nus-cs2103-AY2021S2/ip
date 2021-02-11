package chadbot;

import java.io.IOException;

import chadbot.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class Main extends Application {
    /** Duke object which crafts responses for user inputs. */
    private Chadbot chadbot = new Chadbot();

    /**
     * Runs the GUI for the Duke program.
     *
     * @param stage The stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            String filename = "/view/MainWindow.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(filename));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(chadbot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
