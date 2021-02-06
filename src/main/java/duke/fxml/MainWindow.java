package duke.fxml;

import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import duke.ui.Ui;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Initializes the MainWindow. Displays the Duke greeting.
     */
    @FXML
    public void initialize() {
        scrollPaneSetup();
        showGreetings();
    }

    private void scrollPaneSetup() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private void showGreetings() {
        Ui ui = new Ui();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.getGreeting(), dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
        addToDialogContainer(userDialog, dukeDialog);
        userInput.clear();
        handleExit(input);
    }

    private void addToDialogContainer(DialogBox userDialog, DialogBox dukeDialog) {
        dialogContainer.getChildren().addAll(userDialog, dukeDialog);
    }

    private void handleExit(String input) {
        if (input.equals("bye")) {
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            };
            timer.schedule(timerTask, 700);
        }
    }
}
