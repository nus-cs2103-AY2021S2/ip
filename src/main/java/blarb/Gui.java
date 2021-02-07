package blarb;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Main GUI for Blarb.
 */
public class Gui extends AnchorPane {
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image bot = new Image(this.getClass().getResourceAsStream("/images/blarb.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Blarb blarb;
    private boolean canTerminate = false;

    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("You are now in the presence of Blarb. Speak.", bot)
        );
    }

    /**
     * Sets the Blarb bot to the GUI.
     *
     * @param b Blarb
     */
    public void setBlarb(Blarb b) {
        blarb = b;
    }

    /**
     * Handles the user input
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = blarb.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, bot)
        );
        userInput.clear();
        canTerminate = blarb.willTerminate(userText) || canTerminate;
    }

    @FXML
    private void exit() throws InterruptedException {
        if (canTerminate)  {
            Thread.sleep(1000);
            Platform.exit();
        }
    }
}
