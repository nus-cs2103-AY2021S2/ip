import java.io.IOException;

import quackers.Quackers;
import quackers.ui.javafx.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Quackers using FXML.
 */
public class Main extends Application {

    private static final String TITLE = "Quackers";
    private Image appIcon = new Image("/images/AppIcon.png");

    private Quackers quackers = new Quackers();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            fxmlLoader.<MainWindow>getController().setQuackers(this.quackers);
            stage.setTitle(Main.TITLE);
            stage.setResizable(false);
            stage.getIcons().add(this.appIcon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
