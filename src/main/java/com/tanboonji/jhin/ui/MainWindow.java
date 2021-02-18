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

    private static final String RESTART_MESSAGE = "Please delete data files on local disk and restart the application.";
    private static final int TWO_SECOND = 2000;
    private static final int SEVEN_SECOND = 7000;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserIcon.jpg"));
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
    private boolean isShuttingDown = false;

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
        try {
            String response = this.jhin.initialise();
            dialogContainer.getChildren().add(DialogBox.getJhinDialog(response, jhinImage));
        } catch (JhinException e) {
            dialogContainer.getChildren().add(DialogBox.getJhinDialog(e.getMessage(), errorImage));
            dialogContainer.getChildren().add(DialogBox.getJhinDialog(RESTART_MESSAGE, errorImage));
            forceShutdown(SEVEN_SECOND);
        }
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
            forceShutdown(TWO_SECOND);
        }
    }

    private void forceShutdown(int time) {
        if (!isShuttingDown) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                }
            }, time);
        }
        isShuttingDown = true;
    }
}
