package gui;

import java.io.IOException;

import dukeproject.Duke;
import dukeproject.DukeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Duke duke = new Duke("data/duke.txt");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().addAll(this.getClass().getResource("/stylesheet/Styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Duke Project");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }
}
