package duke.ui;

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

    private UI ui = new UI();

    /**
     * Sets up the GUI.
     *
     * @param stage JavaFX stage for the task bot application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setUI(ui);
            stage.setTitle("Tabby the Task Bot");
            stage.getIcons().add(new Image("/images/tabby icon.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
