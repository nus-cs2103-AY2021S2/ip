import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Ui.greeting(), dukeImage, Color.BEIGE));
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
    private void handleUserInput() throws IOException, InterruptedException {
        String input = userInput.getText();
        if (input.equals("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog("I am a sore loser bye!", dukeImage, Color.BEIGE));
            duke.exit();
            userInput.clear();
        } else {
            try {
                String response = duke.getResponse(input);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage, Color.ALICEBLUE),
                        DialogBox.getDukeDialog(response, dukeImage, Color.BEIGE)
                );
                userInput.clear();
            } catch (DukeException | IOException e) {

                System.out.println(e.getMessage());

            }

        }

    }
}
