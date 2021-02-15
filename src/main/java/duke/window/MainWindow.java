package duke.window;

import java.io.FileNotFoundException;
import java.util.Locale;

import duke.Duke;
import duke.commands.ByeCommand;
import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.InvalidFileTaskTypeException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke, Stage stage) {
        this.duke = duke;
        this.stage = stage;
    }

    /**
     * Creates and displays a DialogBox with introduction message from Duke. If initialising fails, the appropriate
     * error message will be displayed.
     */
    public void displayDukeIntroduction() {
        try {
            String introductionMessage = duke.introduction();
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(introductionMessage, dukeImage)
            );
        } catch (FileNotFoundException e) {
            String cannotAccessFileMsg = "Cannot access file at specified location.\n" + e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeErrorDialog("Error! " + cannotAccessFileMsg, dukeImage)
            );
        } catch (InvalidFileTaskTypeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeErrorDialog("Error! " + e.getMessage(), dukeImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. Closes window after delay if user inputs a "bye"
     * command.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().toLowerCase(Locale.ROOT);

        try {
            String response = duke.getResponse(userInput.getText());

            if (input.equals(ByeCommand.COMMAND_WORD)) {
                PauseTransition delay = new PauseTransition(Duration.seconds(5));
                delay.setOnFinished(event -> stage.close());
                delay.play();
            }

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userInput.getText(), userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userInput.getText(), userImage),
                    DialogBox.getDukeErrorDialog("Error! " + e.getMessage(), dukeImage)
            );
        } finally {
            userInput.clear();
        }
    }
}
