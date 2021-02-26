package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * GUI for Duke using FXML.
 */
public class Main extends Application {
    private static Duke alfred = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("view/StyleSheet.css");
            stage.setScene(scene);
            stage.setTitle("Butler Alfred");
            fxmlLoader.<MainWindow>getController().setDuke(alfred);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
