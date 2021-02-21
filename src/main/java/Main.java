import java.io.IOException;

import quackers.Quackers;
import quackers.ui.javafx.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Image appIcon = new Image("/images/AppIcon.png");

    private Quackers quackers = new Quackers();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(this.appIcon);
            fxmlLoader.<MainWindow>getController().setQuackers(this.quackers);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
