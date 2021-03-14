package jaryl.duke;

import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller that provides the layout for other controls
 */
public class MainWindow {
    private Duke duke;

    @FXML
    private ScrollPane scroller;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private VBox dialogBox;

    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/ash.png"));
    private Image dukeImg = new Image(this.getClass().getResourceAsStream("/images/pikachu.png"));

    /**
     * Sets duke
     * @param duke the Duke chat bot
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        Output output = new Output();
        dialogBox.getChildren().add(Dialog.getDukeDialog(output.printWelcomeMsg(), dukeImg));
    }

    @FXML
    public void initialize() {
        scroller.vvalueProperty().bind(dialogBox.heightProperty());
    }

    /**
     * Handles text updates
     * Enables/disables send button depending on text field content
     */
    public void textUpdateHandler() {
        if (sendButton.isDisabled() && !userInput.getText().isBlank()) {
            sendButton.setDisable(false);
        }
        if (!sendButton.isDisabled() && userInput.getText().isBlank()) {
            sendButton.setDisable(true);
        }
    }

    /**
     * Handles user input. Creates 2 dialog boxes containing Duke's response and user input.
     * User input is flushed after the dialog boxes are processed
     */
    @FXML
    private void userInputHandler() {
        String input = userInput.getText();
        String resp = duke.getResponse(input);
        dialogBox.getChildren().addAll(
                Dialog.getUserResponse(input, userImg),
                Dialog.getDukeResponse(resp, dukeImg)
        );
        userInput.clear();
    }
}
