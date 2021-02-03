package project;

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

    private Olaf olaf;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image olafImage = new Image(this.getClass().getResourceAsStream("/images/olaf-round.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setOlaf(Olaf o) {
        olaf = o;
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
                DialogBox.getUserDialog(input),
                DialogBox.getDukeDialog(response)
//                DialogBox.getUserDialog(input, userImage),
//                DialogBox.getDukeDialog(response, olafImage)
        );
        userInput.clear();
    }
}