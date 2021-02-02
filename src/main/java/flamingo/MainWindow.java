package flamingo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for flamingo.MainWindow. Provides the layout for the other controls.
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

    private Flamingo flamingo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/smile.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/flamingo.png"));

    /**
     * Initialises the chat bot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getFlamingoDialog(Ui.sayHello(), botImage)
        );
    }

    public void setFlamingo(Flamingo f) {
        flamingo = f;
    }

    /**
     * Creates two dialog boxes for user input and response.
     * Appends dialog boxes to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = flamingo.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getFlamingoDialog(response, botImage)
        );
        userInput.clear();
    }
}
