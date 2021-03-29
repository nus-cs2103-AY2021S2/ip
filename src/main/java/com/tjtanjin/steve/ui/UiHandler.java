package com.tjtanjin.steve.ui;

import com.tjtanjin.steve.Steve;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private static FXMLLoader fxmlLoader;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Steve steve;

    //application theme and characters used are from minecraft and is strictly for learning purposes
    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image STEVE_IMAGE = new Image(this.getClass().getResourceAsStream("/images/steve.png"));

    /**
     * Initialize ui window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMsg();
    }

    /**
     * Gets fxml loader.
     * @return fxml loader
     */
    public static FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    /**
     * Sets private attribute Steve.
     *
     * @param steve steve parsed from main class
     */
    public void setSteve(Steve steve, FXMLLoader loader) {
        this.steve = steve;
        fxmlLoader = loader;
    }

    /**
     * Greets the user upon program launch.
     */
    public void showWelcomeMsg() {
        showInfoMsg("Info: Hello, I am Steve! How can I help you?");
    }

    /**
     * Prints information to the user.
     *
     * @param info information to print
     */
    private void showInfoMsg(String info) {
        dialogContainer.getChildren().add(DialogBox.getSteveDialog(info.split(" ", 2)[1], STEVE_IMAGE));
    }

    /**
     * Prints error message to the user.
     *
     * @param msg error message to print
     */
    private void showErrorMsg(String msg) {
        dialogContainer.getChildren().add(DialogBox.getSteveDialog(msg, STEVE_IMAGE));
    }

    /**
     * Terminates the program with a final message.
     *
     * @param crashMsg message to print before termination
     */
    private void showTerminateMsg(String crashMsg) {
        dialogContainer.getChildren().add(DialogBox.getSteveDialog(crashMsg, STEVE_IMAGE));
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
        //special case of termination command
        if (input.split(" ", 2)[0].equalsIgnoreCase("BYE")) {
            System.exit(0);
        }
    }

    /**
     * Checks response and print as info or error.
     *
     * @param response error message to print
     */
    public void showResponse(String response) {
        if (response.startsWith("Error:")) {
            showErrorMsg(response);
        } else if (response.startsWith("Terminated: ")) {
            showTerminateMsg(response);
        } else {
            showInfoMsg(response);
        }
    }
}
