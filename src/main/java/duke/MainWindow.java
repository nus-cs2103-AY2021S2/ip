package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * The variables of this Object is specified in the .fxml file in the resource folder.
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

    private String byeChecker = "-----------------------------------\n"
            + "Bye. No more inputs will be accepted, please close the program!\n"
            + "Hope to see you again soon :)"
            + "\n-----------------------------------\n";
    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/you.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));

    /**
     * Initialize MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        BackgroundFill bgfBisque = new BackgroundFill(
                Color.BISQUE,
                new CornerRadii(10),
                null
        );
        userInput.setBackground(new Background(bgfBisque));

    }

    /**
     * Passes the Duke class so its methods can be used by MainWindow.
     *
     * @param d The initialized Duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
        String response = duke.getResponse("intro");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * If user inputs "bye", no more inputs will be accepted.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (response.equals(byeChecker)) {
            userInput.setEditable(false);
            userInput.setVisible(false);
            userInput.setDisable(true);
            sendButton.setVisible(false);
            sendButton.setDisable(true);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
