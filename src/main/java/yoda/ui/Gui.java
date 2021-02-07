package yoda.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import yoda.Yoda;

public class Gui extends Application{

    private Yoda yoda = new Yoda();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader((Gui.class.
                    getResource("/view/MainWindow.fxml")));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYoda(yoda);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

