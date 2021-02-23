package com.nus.duke.ui;

import java.io.IOException;
import java.net.URL;

import com.nus.duke.Launcher;
import com.nus.duke.command.Command;
import com.nus.duke.command.ExitCommand;
import com.nus.duke.common.Logic;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * MainWindow class is the controller for MainWindow.fxml file.
 */
public class MainWindow {

    private static final String FXML = "/views/MainWindow.fxml";
    private final Logic logic;

    private final Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/cat.jpg"));
    private final Image botImage = new Image(
            this.getClass().getResourceAsStream("/images/woman.jpg"));

    @FXML
    private TextField commandTextField;

    @FXML
    private VBox dialogContainer;

    @FXML
    private ScrollPane scrollPane;

    public MainWindow(Stage primaryStage, Logic logic) {
        this.logic = logic;
        URL fileUrl = Launcher.class.getResource(FXML);
        FXMLLoader fxmlLoader = new FXMLLoader();
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

    /**
     * Initializes the GUI by setting setting up the scrollpane height and displaying a default
     * welcome message.
     */
    @FXML
    public void initialize() {
        this.dialogContainer.heightProperty()
                .addListener((observable -> scrollPane.setVvalue(1.0)));
        this.dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(
                        "Hello! I am Duke.\nWhat can I do for you my dude?",
                        botImage)
        );
    }

    /**
     * Handles the text entered by a user by executing it. Will create a DialogBox for the entered
     * text and the result.
     */
    @FXML
    private void handleCommandText() {
        assert logic != null;

        Command command = this.logic.parseInputForCommand(this.commandTextField.getText());
        assert command != null : "Command should not be null";
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
