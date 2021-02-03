package soonkeatneo.duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Main Window implementation for the Duke chat-bot.
 *
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 2 iP v0.1
 */
public class MainWindow extends AnchorPane {

    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button sendButton;
    @FXML
    private TextField userInput;
    private Duke dukeBot;
    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream
            ("/images/user.jpg"));
    private final Image DUKE_IMAGE = new Image(this.getClass().getResourceAsStream
            ("/images/dukenukem.jpg"));

    /**
     * Initializes the main window with the parameters and greets the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greetUser();
    }

    /**
     * Sets the instance of the bot to be used
     *
     * @param duke the Duke bot instance
     */
    public void setDuke(Duke duke) {
        this.dukeBot = duke;
    }

    /**
     * Greets user with a message on the screen when called.
     */
    public void greetUser() {
        printMessage("Henlooooo~! My name is " + Duke.BOT_NAME
                + ". What can I do for you today? :)");
    }

    /**
     * Prints messages to the user.
     *
     * @param msg message to be printed
     */
    private void printMessage(String msg) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(msg, DUKE_IMAGE));
    }

    /**
     * Handles user input and provides for the printing of returned messages.
     */
    @FXML
    private void handleUserInput() {
        String inputString = userInput.getText().strip();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(inputString, USER_IMAGE)
        );
        userInput.clear();
        printMessage(dukeBot.handleInput(inputString));
    }

}
