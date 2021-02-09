package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("data/dukeData.txt");

    /**
     * The main entry point for the GUI.
     * Creates the scene, duke and display greetings.
     *
     * @param stage Window on which scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            SplitPane sp = fxmlLoader.load();
            Scene scene = new Scene(sp);

            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/Icon.png")));
            stage.setScene(scene);
            stage.setTitle("Duke");

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().showGreetings();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
