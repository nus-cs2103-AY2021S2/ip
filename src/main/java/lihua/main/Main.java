package lihua.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lihua.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String GUI_RESOURCE_PATH = "/view/MainWindow.fxml";
    private Lihua lihua = new Lihua();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(GUI_RESOURCE_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Lihua");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLihua(lihua);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
