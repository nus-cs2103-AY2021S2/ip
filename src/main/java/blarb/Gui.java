package blarb;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getBlarbDialog("You are now in the presence of Blarb. Speak.", bot)
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
        String[] blarbText = blarb.getResponse(userInput.getText());
        boolean isNotice = Boolean.parseBoolean(blarbText[1]);
        DialogBox userBox = DialogBox.getUserDialog(userText, user);
        DialogBox blarbBox = isNotice
                ? DialogBox.getWarnDialog(blarbText[0], bot)
                : DialogBox.getBlarbDialog(blarbText[0], bot);
        dialogContainer.getChildren().addAll(userBox, blarbBox);
        userInput.clear();
        if (blarb.willTerminate(userText)) {
            terminate();
        }
    }

    /**
     * Terminates the application
     */
    @FXML
    private void terminate() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");
        alert.showAndWait()
                .filter(x -> x.equals(ButtonType.OK))
                .map(x -> (Runnable) Platform::exit)
                .orElse(() -> {
                })
                .run();
        dialogContainer.getChildren().addAll(
                DialogBox.getBlarbDialog("Or maybe not.", bot)
        );
    }
}
