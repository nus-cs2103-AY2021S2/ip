package chandler.ui;

import chandler.Chandler;
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

    private Chandler chandler;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/joey.jpg"));
    private Image chandlerImage = new Image(this.getClass().getResourceAsStream("/images/chandler.jpg"));

    /**
     * Initialize the UI elements in the MainWindow.
     *
     * @param chandler The Chandler object handling all the logic.
     */
    @FXML
    public void initialize(Chandler chandler) {
        this.chandler = chandler;
        String greeting = chandler.initializeStart();
        dialogContainer.getChildren().addAll(
                DialogBox.getChandlerDialog(greeting, chandlerImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Chandler's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chandler.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChandlerDialog(response, chandlerImage)
        );
        userInput.clear();
    }
}
