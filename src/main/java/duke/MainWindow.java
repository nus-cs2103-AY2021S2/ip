package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String IMAGE_FILEPATH_USER = "/images/User.png";
    private static final String IMAGE_FILEPATH_BOT = "/images/Bot.png";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Main main;

    private Image userImage = new Image(this.getClass().getResourceAsStream(IMAGE_FILEPATH_USER));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(IMAGE_FILEPATH_BOT));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    public void greet() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.greet(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        // String response = duke.getResponse(input);
        Pair<Integer, String> response = duke.getResponse(input);
        if (response.getKey() != 0) {
            Platform.exit();
            System.exit(0);
        }
        String responseString = response.getValue();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(responseString, dukeImage)
        );
        userInput.clear();
    }
}
