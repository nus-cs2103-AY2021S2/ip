import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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

    private Percy percy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/percy.png"));


    /**
     * Initialises the elements.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showStartUpMessage();
    }

    public void setPercy(Percy p) {
        percy = p;
    }

    /**
     * Displays the start-up message.
     */
    public void showStartUpMessage() {
        String msg = "Woof! I am Percy! How can I help you?";
        dialogContainer.getChildren().add(DialogBox.getPercyDialog(msg, dukeImage));
    }

    /**
     * Shows dialogue boxes according to input.
     * @param input input of user.
     * @param response response of Percy.
     */
    public void showDialogue(String input, String response) {
        if (input.isEmpty()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getPercyDialog(response, dukeImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getPercyDialog(response, dukeImage));
        }
    }

    /**
     * Exits the program if bye command.
     * @param input input to be checked.
     */
    public void exitIfBye(String input) {
        if (input != null && input.equals("bye")) {
            CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(() -> {
                System.exit(0);
            });
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = percy.getResponse(input, percy);
        showDialogue(input, response);
        userInput.clear();
        exitIfBye(input);
    }
}
