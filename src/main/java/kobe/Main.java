package kobe;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String HOME = System.getProperty("user.home");

    @Override
    public void start(Stage stage) {
        try {
            Path path = Paths.get(HOME + "/ip/src/main/data/kobe.txt");
            String pathName = HOME + "/ip/src/main/data/kobe.txt";
            KobeN kobe = new KobeN(pathName);
//            kobe.run();
            FXMLLoader fxmlLoader = new FXMLLoader(kobe.Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKobe(kobe);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}