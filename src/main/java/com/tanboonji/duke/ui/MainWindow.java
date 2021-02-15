package com.tanboonji.duke.ui;

import java.util.Timer;
import java.util.TimerTask;

import com.tanboonji.duke.Duke;
import com.tanboonji.duke.exception.DukeException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class is the controller for MainWindow fxml.
 * Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/HeimerdingerIcon.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/JhinIcon.jpg"));
    private final Image errorImage = new Image(this.getClass().getResourceAsStream("/images/ErrorIcon.jpg"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;

    @FXML
    public void initialize() {
    }

    /**
     * Sets and initialises Duke to perform execution of commands.
     *
     * @param duke Duke to be initialised to.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        String response = this.duke.initialise();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String response;
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        try {
            response = duke.getResponse(input);
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        } catch (DukeException e) {
            response = e.getMessage();
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, errorImage));
        }
        userInput.clear();

        scrollPane.applyCss();
        scrollPane.layout();
        scrollPane.setVvalue(scrollPane.getVmax());

        if (duke.isShuttingDown()) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                }
            }, 2000);
        }
    }
}
