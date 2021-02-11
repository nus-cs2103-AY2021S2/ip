package project;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.common.PrintedText;

/**
 * Application GUI using FXML
 */
public class MainApp extends Application {
    private Olaf olaf = new Olaf();

    /**
     * Starts a session of the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load(); //breaks here cos it cant load MainWindow
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Olaf");
            fxmlLoader.<MainWindow>getController().setOlaf(olaf);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
