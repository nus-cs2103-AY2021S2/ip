import java.io.IOException;

import duke.Duke;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private static final String STORAGE_PATH = "data/tasks.txt";
    private Duke duke = new Duke(STORAGE_PATH);

    /**
     * Exits and closes the application after 3 seconds.
     */
    public static void exit() {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            Platform.exit();
        });
        pause.play();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("DukeLukeMuke");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
