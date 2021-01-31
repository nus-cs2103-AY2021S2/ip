import commands.CommandResponse;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ui.Snomio;

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

    private Snom snom;
    private Snomio snomio = new Snomio();

    private Image userImage = new Image(this.getClass().getResourceAsStream("images/DaUser.png"));
    private Image snomImage = new Image(this.getClass().getResourceAsStream("images/DaSnom.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getSnomDialog(snomio.showWelcomeMsg(), snomImage)
        );
    }

    public void setSnom(Snom snom) {
        this.snom = snom;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Snom's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResponse response = snom.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSnomDialog(response.getResponseMsg(), snomImage)
        );
        userInput.clear();
        if(response.isExit()){
            Platform.exit();
        }
    }
}