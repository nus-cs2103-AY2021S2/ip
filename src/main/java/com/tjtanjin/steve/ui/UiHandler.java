package com.tjtanjin.steve.ui;

import com.tjtanjin.steve.Steve;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for UiHandler. Provides the layout for the other controls.
 */
public class UiHandler extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Steve steve;

    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image STEVE_IMAGE = new Image(this.getClass().getResourceAsStream("/images/steve.png"));

    /**
     * Initialize ui window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    /**
     * Set private attribute Steve.
     *
     * @param steve steve parsed from main class
     */
    public void setSteve(Steve steve) {
        this.steve = steve;
    }

    /**
     * Greets the user upon program launch.
     */
    public void showWelcome() {
        showInfo("Hello, I am Steve! How can I help you?");
    }

    /**
     * Prints information to the user.
     *
     * @param info information to print
     */
    public void showInfo(String info) {
        dialogContainer.getChildren().add(DialogBox.getSteveDialog(info.split(" ", 2)[1], STEVE_IMAGE));
    }

    /**
     * Prints error message to the user.
     *
     * @param msg error message to print
     */
    public void showError(String msg) {
        dialogContainer.getChildren().add(DialogBox.getSteveDialog(msg, STEVE_IMAGE));
    }

    /**
     * Handles the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, USER_IMAGE)
        );
        userInput.clear();
        showResponse(steve.getParser().parseInput(input));
    }

    /**
     * Checks response and print as info or error.
     *
     * @param response error message to print
     */
    public void showResponse(String response) {
        if (response.startsWith("Error:")) {
            showError(response);
        } else {
            showInfo(response);
        }
    }

    /**
     * Terminates the program with a final message.
     *
     * @param crashMsg message to print before termination
     */
    public static void terminate(String crashMsg) {
        System.out.println(crashMsg);
        System.exit(0);
    }
}
