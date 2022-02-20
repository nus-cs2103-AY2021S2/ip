package yoda.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yoda.Yoda;

/**
 * Gui class to start up Yoda's Gui and set the stage.
 */
public class Gui extends Application {
    /** Yoda object for which a Gui is being created*/
    private Yoda yoda = new Yoda();

    /**
     * Starts up the Gui for Yoda.
     * @param stage Stage of Gui.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader((Gui.class
                    .getResource("/view/MainWindow.fxml")));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYoda(yoda);
            fxmlLoader.<MainWindow>getController().greetUser();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

