import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import myDuke.MyDuke;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private MyDuke myDuke = new MyDuke("../data/saveFile.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(myDuke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}