package zeke;

import java.util.Timer;
import java.util.TimerTask;

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

    private Zeke zeke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/AttackTitan.jpg"));
    private Image zekeImage = new Image(this.getClass().getResourceAsStream("/images/BeastTitan.jpg"));

    /**
     * Creates a window and greets user
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getZekeDialog(Ui.greetings(), zekeImage)
        );
    }

    public void setZeke(Zeke d) {
        zeke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Zeke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = zeke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZekeDialog(response, zekeImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            TimerTask closeApp = new TimerTask() {
                public void run() {
                    System.exit(0);
                }
            };
            Timer timer = new Timer();
            timer.schedule(closeApp, 1500);
        }
    }
}
