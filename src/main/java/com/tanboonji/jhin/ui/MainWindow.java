package com.tanboonji.jhin.ui;

import java.util.Timer;
import java.util.TimerTask;

import com.tanboonji.jhin.Jhin;
import com.tanboonji.jhin.exception.JhinException;

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
    private final Image jhinImage = new Image(this.getClass().getResourceAsStream("/images/JhinIcon.jpg"));
    private final Image errorImage = new Image(this.getClass().getResourceAsStream("/images/ErrorIcon.jpg"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Jhin jhin;

    @FXML
    public void initialize() {
    }

    /**
     * Sets and initialises Jhin to perform execution of commands.
     *
     * @param jhin Jhin to be initialised to.
     */
    public void setJhin(Jhin jhin) {
        this.jhin = jhin;
        String response = this.jhin.initialise();
        dialogContainer.getChildren().add(DialogBox.getJhinDialog(response, jhinImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jhin's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String response;
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        try {
            response = jhin.getResponse(input);
            dialogContainer.getChildren().add(DialogBox.getJhinDialog(response, jhinImage));
        } catch (JhinException e) {
            response = e.getMessage();
            dialogContainer.getChildren().add(DialogBox.getJhinDialog(response, errorImage));
        }
        userInput.clear();

        scrollPane.applyCss();
        scrollPane.layout();
        scrollPane.setVvalue(scrollPane.getVmax());

        if (jhin.isShuttingDown()) {
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
