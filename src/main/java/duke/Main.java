package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String APP_NAME = "Duke";
    private final Duke duke = new Duke();
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/squareface.png"));

    @Override
    public void start(Stage stage) {
        // Solution below adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.getIcons().add(dukeImage);
            stage.setTitle(APP_NAME);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
