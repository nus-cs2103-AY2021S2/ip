import duke.Duke;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A GUI for duke using Javafx
 *
 * @param duke is the duke logic object
 * @author WangYihe
 * @author E0424695
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private MainWindow mainWindow = new MainWindow();
    private boolean isStarting = true;

    @Override
    public void start(Stage stage) {
        mainWindow.initialize();
        Scene scene = new Scene(mainWindow.mainLayout);
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMaxHeight(400.0);
        stage.getIcons().add(mainWindow.dukeImage);

        mainWindow.setDuke(duke);
        mainWindow.initializeComponentProperties();
        mainWindow.initializeEventListeners();

        if (isStarting) {
            mainWindow.getChildren().add(
                    DialogBox.getDukeDialog(duke.greeting(), mainWindow.dukeImage)
            );
            isStarting = false;
        }
    }
}
