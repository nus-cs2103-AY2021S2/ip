package duke.ui;

import duke.Duke;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Mando.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Yoda.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
        String response = duke.run(input);
        if (response.startsWith("OOPS")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getErrorDialog(response, dukeImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        //@@author wongkokian-reused
        //Reused from https://github.com/jonfoocy/ip with minor modifications
        if (input.equals("bye")) {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished((event) -> stage.close());
            delay.play();
        }
        //@@author
        userInput.clear();
    }

    /**
     * Shows welcome message to the user.
     */
    //@@author wongkokian-reused
    //Reused from https://github.com/jonfoocy/ip with minor modifications
    public void welcome() {
        String welcomeText = "Welcome to YodaBot!\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeText, dukeImage));
    }
    //@@author
}
