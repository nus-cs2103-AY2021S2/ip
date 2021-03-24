package duke;

import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the main entry point into the application.
 * Inspired by https://github.com/sc-arecrow/ip.
 */
public class Main extends Application {
    /**
     * Starts the GUI of Duke.
     *
     * @param stage Stage shown.
     */
    @Override
    public void start(Stage stage) {
        AnchorPane anchorPane = new MainWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/applicationIcon.png")));
        stage.show();
    }
}
