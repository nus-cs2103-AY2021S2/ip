package monica.ui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import monica.Monica;

/**
 * Controller for MainWindow.
 * Provides the layout for the other controls.
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

    private Monica monica;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image monicaImage = new Image(this.getClass().getResourceAsStream("/images/Monica.png"));

    /**
     * Initializes dialogs
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getMonicaDialog(Ui.showWelcome(), monicaImage));
    }

    /**
     * Sets up Monica
     */
    public void setMonica(Monica monica) {
        this.monica = monica;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Monica's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = monica.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMonicaDialog(response, monicaImage)
        );

        if (input.equals("bye")) {
            new Timer().schedule(new TimerTask() {
                public void run () {
                    System.exit(0); }
            }, 3000);
        }

        userInput.clear();
    }
}
