package duke.ui;

import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import duke.exception.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow.
 * Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage =
        new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage =
        new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Completes the init of duke object by loading Ui and storage.
     * This can be improved to follow OOP better.
     *
     * @param duke duke object created from Main class.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        try {
            duke.loadUiAndStorage();
            printResponse(duke.getGreetMessage());
        } catch (DukeException e) {
            String message = e.getMessage() + "\n"
                + "Closing...";
            printResponse(message);
        }
    }

    /**
     * Creates two dialog boxes, and clears the user input after processing.
     * One echoing user input and the other containing Duke's reply and then appends them to the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        boolean isExitCalled = input.equalsIgnoreCase("bye");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        if (isExitCalled) {
            exit();
        }
        userInput.clear();
    }

    /**
     * Exits program for GUI.
     * Delays the closing of GUI for 1.5 seconds so that users can see the exit message.
     */
    private void exit() {
        new Timer().schedule(new TimerTask() {
            public void run () {
                System.exit(0);
            }
        }, 1500);
    }

    @FXML
    private void printResponse(String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }
}
