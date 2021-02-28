package main.duke.gui;

import main.duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import java.io.IOException;

public class Main extends Application {

    private Duke duke = new Duke("data/duke.txt");
    @Override
    public void start(Stage stage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane  = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.setTitle("Duke bot");
            fxmlLoader.<MainWindow>getController().makeDuke(duke);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
