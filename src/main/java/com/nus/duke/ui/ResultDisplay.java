package com.nus.duke.ui;

import java.io.IOException;
import java.net.URL;

import com.nus.duke.Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;

public class ResultDisplay {

    private static final String FXML = "/views/ResultDisplay.fxml";
    private final FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private TextArea resultDisplay;

    public ResultDisplay() {
        URL fileUrl = Main.class.getResource(FXML);
        fxmlLoader.setLocation(fileUrl);
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(null);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
