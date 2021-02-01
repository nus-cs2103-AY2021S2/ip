package com.tjtanjin.steve;

import java.io.IOException;

import com.tjtanjin.steve.ui.UiHandler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Steve using FXML.
 */
public class Main extends Application {

    private final Steve steve = new Steve();

    /**
     * Overrides default start method of application in javafx.
     *
     * @param stage primary stage of javafx
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/UiHandler.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<UiHandler>getController().setSteve(steve, fxmlLoader);
            stage.show();
            stage.setTitle("Steve");
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
