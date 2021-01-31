package duke.controller;

import java.util.Timer;
import java.util.TimerTask;

import duke.bot.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /** A ScrollPane element that represent a scrollbar in the window */
    @FXML
    private ScrollPane scrollPane;
    /** A container element the represent a box that displays dialog boxes */
    @FXML
    private VBox dialogContainer;
    /** A text input element whereby the user can issue commands via typing */
    @FXML
    private TextField userInput;
    /** A button element whereby the user can send the command to the bot */
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat1.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/cat2.png"));

    /**
     * Initialize any properties of this UI element and its sub-elements
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set a reference to the Duke chat bot
     * @param d A Duke chat bot
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Perform any operations required after the window opens
     */
    public void start() {
        String msg = duke.getBootupMsg();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(msg, dukeImage));
    }

    /**
     * Perform any cleanup operations before the window closes
     */
    private void end() {
        // Prints an exit message and exit after 0.5 seconds
        String msg = duke.getExitMsg();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(msg, dukeImage));

        long delay = 500;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, delay);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        if (duke.hasClosed()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage)
            );
            end();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }

        userInput.clear();
    }
}
