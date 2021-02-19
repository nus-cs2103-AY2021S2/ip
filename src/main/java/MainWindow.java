import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


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

    private Duke duke;

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/trump.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User-Biden.png"));


    /**
     * Initialize the MainWindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Ui.greeting(), dukeImage, Color.NAVAJOWHITE));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) throws IOException {
        duke = d;
        duke.run();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        if (input.equals("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog("I am a sore loser bye!", dukeImage, Color.NAVAJOWHITE));
            duke.exit();
            userInput.clear();
            Platform.exit();
        } else {
            try {
                String response = duke.getResponse(input);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage, Color.ALICEBLUE),
                        DialogBox.getDukeDialog(response, dukeImage, Color.NAVAJOWHITE)
                );
                userInput.clear();
            } catch (DukeException | IOException e) {

                dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(e.toString(),
                        dukeImage, Color.NAVAJOWHITE));

            }

        }

    }
}
