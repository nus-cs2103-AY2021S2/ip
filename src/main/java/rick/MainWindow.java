package rick;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for duke.MainWindow. Provides the layout for the other controls.
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

    private Rick rick;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/MortyAvatar.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/RickAvatar.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getRickDialog(Gui.getWelcomeString(), dukeImage));
    }

    public void setDuke(Rick d) {
        rick = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rick.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRickDialog(response, dukeImage)
        );
        if (input.equalsIgnoreCase(Command.BYE.name())) {
            TimerTask closeApp = new TimerTask() {
                public void run() {
                    System.exit(0);
                }
            };
            new Timer().schedule(closeApp, 1500);
        }
        userInput.clear();
    }
}