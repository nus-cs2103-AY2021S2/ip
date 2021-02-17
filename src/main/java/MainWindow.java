import format.Ui;
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
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Ui.introMessage(), dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
        runTests();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (duke.hasExitCommandBeenSent()) {
            handleExit();
        }
    }

    /**
     * This method simulates some user input on launcher startup, for easier testing.
     */
    private void runTests() {
        String[] inputs = {
                "list",
                "todo hello",
                "done 1",
                "delete 1",
                "find hello",
                "event read /at",
                "deadline item /by 16:11 4-12",
                "e yelp /at 11:58 pm 3/4",
                "dl ye /by 15:30pm 4/12",
                "help",
                "delete 1",
                "done 1",
                "list",
                // todo test everything that's supposed to throw exceptions
                "event read /at 2pm 3/4",
                "deadline /by 16:11 4-12",
                "deadline 16:11 4-12",
                "deadline ye /by 15:30pm 4/19",
                "done 0",
                "delete 0",
                "delete 100",
                "done 100",
                "list"
        };

        for (String i : inputs) {
            runTest(i);
        }
    }

    private void runTest(String input) {
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (duke.hasExitCommandBeenSent()) {
            handleExit();
        }
    }

    private void handleExit() {
        Platform.exit();
    }
}