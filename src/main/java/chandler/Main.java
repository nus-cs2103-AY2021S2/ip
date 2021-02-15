package chandler;

import java.io.IOException;

import chandler.ui.MainWindow;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Main class encapsulates the main environment where the application runs.
 */
public class Main extends Application {
    private static final String PATH_NAME = "./data/saved_task_list.txt";

    private Chandler chandler = new Chandler(PATH_NAME);

    @Override
    public void start(Stage stage) {
        try {
            assert chandler != null : "chandler object cannot be null";

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().initialize(chandler);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses for 2 seconds then exits the program if no other threads are running.
     */
    public static void exit() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            Platform.exit();
        });
        pause.play();
    }
}
