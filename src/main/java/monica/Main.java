package monica;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import monica.ui.MainWindow;

/**
 * A main class to start application in javafx.
 */
public class Main extends Application {
    private final Monica monica = new Monica();

    /**
     * Overrides default start method of application in javafx.
     *
     * @param stage Primary stage of javafx
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "JavaFX cannot be loaded.";
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMonica(monica);
            stage.show();
            stage.setTitle("Monica");
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
