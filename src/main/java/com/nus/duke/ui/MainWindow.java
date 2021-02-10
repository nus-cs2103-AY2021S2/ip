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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {

    private static final String FXML = "/views/MainWindow.fxml";
    private final FXMLLoader fxmlLoader = new FXMLLoader();
    private final Logic logic;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/woman.jpg"));

    @FXML
    private TextField commandTextField;

    @FXML
    private VBox dialogContainer;

    @FXML
    private ScrollPane scrollPane;

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
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(
                        "Hello! I am Duke. What can I do for you my dude?",
                        botImage)
        );
    }

    @FXML
    private void handleCommandText(ActionEvent event) {
        Command command = this.logic.parseInputForCommand(this.commandTextField.getText());
        String result = this.logic.executeCommand(command);

        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(this.commandTextField.getText(), userImage),
                DialogBox.getDukeDialog(result, botImage)
        );

        this.commandTextField.clear();

        if (command instanceof ExitCommand) {
            Platform.exit();
        }
    }
}
