package com.weiliang;

import java.io.IOException;

import com.weiliang.ui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main GUI for bot using FXML.
 */
public class Main extends Application {

    private final Duke duke;

    /**
     * Instantiates the JavaFX application.
     */
    public Main() {
        this.duke = new Duke("SimpleBot");
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            // Set up scene information
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle(duke.getName());
            stage.setResizable(false);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
