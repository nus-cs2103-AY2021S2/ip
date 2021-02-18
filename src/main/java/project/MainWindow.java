package project;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import project.common.PrintedText;

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

    private Olaf olaf;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image olafImage = new Image(this.getClass().getResourceAsStream("/images/olaf-head.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets up this MainWindow Controller with the necessary {@code Olaf} object
     * and greets the user.
     */
    public void setOlaf(Olaf o) {
        olaf = o;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(PrintedText.WELCOME_MESSAGE.toString(), olafImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Olaf's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = olaf.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, olafImage)
        );
        userInput.clear();

        // if user says 'bye', wait for 1.5 seconds after displaying goodbye message before closing application
        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds (1.5));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
