package ip.src.main.java;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();


    @Override
    public void start(Stage stage) {
        Storage storage = new Storage("data/duke.txt" , duke);

        try {
            File f = new File("data/duke.txt");
            f.getParentFile().mkdirs();
            if (!f.createNewFile()) {
                storage.createBot("data/duke.txt" , duke);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            String greet = duke.greet();
            Label label = new Label(greet);
            stage.setTitle(greet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}