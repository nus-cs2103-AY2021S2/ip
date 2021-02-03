package com.nus.duke.ui;

import java.io.IOException;
import java.net.URL;

import com.nus.duke.Main;
import com.nus.duke.command.Command;
import com.nus.duke.command.ExitCommand;
import com.nus.duke.common.Logic;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainWindow {

    private static final String FXML = "/views/MainWindow.fxml";
    private final FXMLLoader fxmlLoader = new FXMLLoader();
    private final Logic logic;

    @FXML
    private TextField commandTextField;

    @FXML
    private TextArea resultTextArea;

    public MainWindow(Stage primaryStage, Logic logic) {
        this.logic = logic;
        URL fileUrl = Main.class.getResource(FXML);
        fxmlLoader.setLocation(fileUrl);
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(primaryStage);
        try {
            fxmlLoader.load();
            primaryStage.show();
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCommandText(ActionEvent event) {
        Command command = this.logic.parseInputForCommand(this.commandTextField.getText());
        String result = this.logic.executeCommand(command);
        this.commandTextField.clear();
        this.resultTextArea.setText(result);

        if (command instanceof ExitCommand) {
            Platform.exit();
        }
    }
}
