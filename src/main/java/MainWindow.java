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

    private Luna luna;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user2.jpg"));
    private Image lunaImage = new Image(this.getClass().getResourceAsStream("/images/luna.jpg"));

    /**
     * Initialises fxml.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        initializeGreeting();
    }

    public void setLuna(Luna l) {
        luna = l;
    }

    /**
     * Initialises a greeting dialogBox.
     */
    public void initializeGreeting() {
        dialogContainer.getChildren().addAll(DialogBox.getLunaDialog(Ui.greet(), lunaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Luna's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = luna.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLunaDialog(response, lunaImage)
        );

        if (response.equals(Ui.outputMessageBye())) {
            Platform.exit();
            System.exit(0);
        }
        userInput.clear();
    }
}
