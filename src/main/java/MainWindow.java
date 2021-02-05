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
    private TextField textField;
    @FXML
    private Button sendButton;

    private SurrealChat surrealChat;

    private Image userImage = new Image(getClass().getResourceAsStream("/images/Meme Orang Disguise.png"));
    private Image surrealImage = new Image(getClass().getResourceAsStream("/images/Meme Man.png"));

    /**
     * Initialises the dialog box.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getSurrealDialog(initialGreeting(), surrealImage)
        );
    }

    private String initialGreeting() {
        String initialOutput = "I am Meme Man. Whoms't be entering the VIMension?\n";
        return initialOutput;
    }

    /**
     * Sets the SurrealChat object to property.
     * @param surrealChat The SurrealChat object containining internal logic.
     */
    public void setSurreal(SurrealChat surrealChat) {
        this.surrealChat = surrealChat;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = textField.getText();
        String response = surrealChat.commandLogic(input);
        response += surrealChat.saveFile();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSurrealDialog(response, surrealImage)
        );
        textField.clear();
    }
}
