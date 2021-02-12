package rick;

import java.io.IOException;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;

public class Main extends Application {
    public static final String filePath = "data/duke.txt";
    private Rick rick = new Rick(filePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(rick);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}