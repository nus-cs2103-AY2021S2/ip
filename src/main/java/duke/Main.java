package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class to initialize the application.
 */
public class Main extends Application {
    /**
     * Initializes the application and load the user interface.
     * @param primaryStage Main window.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/interface.fxml"));
            AnchorPane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);

            primaryStage.setTitle("Duke App");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
