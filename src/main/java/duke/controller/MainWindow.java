package duke.controller;

import duke.Duke;
import duke.exception.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialize MainWindow.fxml
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Receives duke object and sets up for MainWindow
     *
     * @param d Duke object
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates a dialog box where Duke welcomes user
     */
    public void welcomeUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.greetUser(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String response = duke.getResponse(input);
            assert dukeImage != null : "Duke Image not found";
            assert userImage != null : "User Image not found";
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog("User:\n" + input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        } catch (DukeException ex) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(ex.getMessage(), dukeImage)
            );
            userInput.clear();
        }
    }
}
