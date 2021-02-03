package soonkeatneo.duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Graphical user interface implementation for the Duke chat-bot.
 *
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 2 iP v0.1
 */
public class Main extends Application {

    private final Duke dukeBot = new Duke("data/data.txt");

    /**
     * Provides an overridden start implementation of JavaFX.
     *
     * @param stage primary stage to be used
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("DukeNukem");
            fxmlLoader.<MainWindow>getController().setDuke(dukeBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
