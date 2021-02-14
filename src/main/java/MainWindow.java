import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private Skeleton skeleton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DiamondHands.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Jozu.png"));
    private Image welcomeImage = new Image(this.getClass().getResourceAsStream("/images/CrazyDiamond.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // welcome message
        DialogBox welcomeMessage = DialogBox.welcomeMessage("Welcome. How can I help you?", welcomeImage);
        dialogContainer.getChildren().addAll(welcomeMessage);
    }

    public void setSkeleton(Skeleton s) {
        skeleton = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = skeleton.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}