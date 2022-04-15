package kelbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Kelbot using FXML.
 */
public class Main extends Application {
    private Kelbot kelbot = new Kelbot();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Kelbot");
            stage.setResizable(false);
            stage.setMinHeight(500.0);
            stage.setMinWidth(400.0);
            fxmlLoader.<MainWindow>getController().setKelbot(kelbot);
            fxmlLoader.<MainWindow>getController().init(kelbot.getTaskList().toString());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
