package lihua.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lihua.commons.Messages;
import lihua.main.Lihua;

/**
 * Controller for lihua.ui.MainWindow. Provides the layout for the other controls.
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

    private Lihua lihua;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/geisha-2-concrete.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/geisha-concrete.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Configures the bot with entity Lihua.
     *
     * @param lihua The entity to be configured.
     */
    public void setLihua(Lihua lihua) {
        this.lihua = lihua;
        sendWelcomMessage();
    }

    /**
     * Sends a welcome message to the user.
     */
    public void sendWelcomMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Messages.MESSAGE_HELLO, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * If the response is null, the application should exit.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lihua.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
