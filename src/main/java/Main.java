import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    private Duke duke = new Duke();

    private int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    private int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            /*
            int sceneWidth = 0;
            int sceneHeight = 0;
            if (screenWidth <= 800 && screenHeight <= 600) {
                sceneWidth = 600;
                sceneHeight = 350;
            } else if (screenWidth <= 1280 && screenHeight <= 768) {
                sceneWidth = 800;
                sceneHeight = 450;
            } else if (screenWidth <= 1920 && screenHeight <= 1080) {
                sceneWidth = 1000;
                sceneHeight = 650;
            }
            */
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            stage.setOnCloseRequest(e-> {
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
