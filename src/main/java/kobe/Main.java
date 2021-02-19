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

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String HOME = System.getProperty("user.home");

    private Image kobeImage = new Image(this.getClass().getResourceAsStream("/images/Loppie says hi.png"));

    @Override
    public void start(Stage stage) {
        try {
            Path path = Paths.get(HOME + "/ip/src/main/data/kobe.txt");
            String pathName = HOME + "/ip/src/main/data/kobe.txt";
            KobeN kobe = new KobeN(pathName);
            FXMLLoader fxmlLoader = new FXMLLoader(kobe.Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKobe(kobe);
            stage.setOnShown((event) -> {
               String response = "Hello! I'm Kobe.\n What can I do for you?";
               VBox dialogContainerTemp = new VBox();
               dialogContainerTemp.getChildren().addAll(
                    DialogBox.getKobeDialog(response, kobeImage));
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}