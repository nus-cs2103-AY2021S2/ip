package ui;

import duke.Duke;
import exception.DukeException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Controller for ui.MainWindow. Provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Initialize.
     */
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
    private void handleUserInput() throws IOException, DukeException {
        try {
            String input = userInput.getText();
            String response = duke.parse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            if (input.equals("bye")) {
                exitDuke();
            }
            userInput.clear();
        } catch (Exception e) {
            Platform.runLater(() -> {
                Alert dialog = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                dialog.setHeaderText("Uh oh...");
                dialog.show();
            });
        }

    }

    /**
     * Exits duke program.
     */
    private void exitDuke() {
        userInput.setOnAction(null);
        sendButton.setOnAction(null);
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> System.exit(0));
        pause.play();
    }
}