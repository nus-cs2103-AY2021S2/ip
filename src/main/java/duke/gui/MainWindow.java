package duke.gui;

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
 * Controller for MainWindow. Provides the layout for the other controls.
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Print welcome msg
        String welcome_msg = "Hello! I am duke\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcome_msg, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void printInteraction(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        try {
            String response = duke.getResponse(input);

            printInteraction(input, response);

            if (duke.isExitInput(input)) {
                System.exit(0);
            }
        } catch (DukeException e) {
            printInteraction(input, e.getMessage());
        } catch (Exception e) {
            System.out.printf("[ERROR] %s: %s\n", e.getClass().getCanonicalName(), e.getMessage());
            e.printStackTrace();

            String DEFAULT_ERR_MSG = "An unknown error occurred. Please try another command " +
                    "or contact the developer for help.";
            printInteraction(input, DEFAULT_ERR_MSG);
        }
    }
}
