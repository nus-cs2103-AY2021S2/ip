import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private SurrealChat surrealChat = SurrealChat.initSurrealChat(new File(SurrealChat.TASK_FILE_PATH));

    /**
     * Starts up the application.
     * @param stage The stage for the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSurreal(surrealChat);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
