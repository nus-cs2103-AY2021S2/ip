import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static int millisecondsToExit = 1500; //delay before closing
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Austen.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Fayola.png"));

    @FXML
    public void initialize() {
        assert(userImage != null);
        assert(dukeImage != null);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.getWelcomeMessage(),dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (input.equals("bye")) {
            userInput.clear();
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            Platform.exit();
                            System.exit(0);
                        }
                    }, millisecondsToExit);
        }
        userInput.clear();
    }

    /**
     * Gives a response based on input
     */
    private String getResponse(String input) {
        assert(duke != null);
        return duke.getResponse(input);
    }
}