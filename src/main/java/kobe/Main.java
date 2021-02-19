package kobe;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

// Main class (below) and FXML files adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
/**
 * A GUI for Kobe using FXML.
 */
public class Main extends Application {
    private static final String HOME = System.getProperty("user.home");

    private Image kobeImage = new Image(this.getClass().getResourceAsStream("/images/Loppie says hi.png"));

    @Override
    public void start(Stage stage) {
        try {
//            Path path = Paths.get(HOME + "/ip/src/main/data/kobe.txt");
            Path path = Paths.get(HOME, "ip", "src", "main", "data", "kobe.txt");
//            String pathName = HOME + "/ip/src/main/data/kobe.txt";
            String pathName = path.toString();
            KobeN kobe = new KobeN(pathName);
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