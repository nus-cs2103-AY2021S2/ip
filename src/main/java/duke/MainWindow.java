package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

//@@ jellymias-reused
// reused from http://https://github.com/jellymias/ip/blob/master/src/main/java/duke/MainWindow.java
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/tenor.gif"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/MAGA.gif"));

    /**
     * Initialises the Main window with the dialogue box
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialogue(Ui.initGreeting(), dukeImage));
    }

    /**
     * Sets d as Duke for the main window
     * @param d
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws Exception {
        String input = userInput.getText();
        // GET THE RESPONSE OF COMMAND IN STRING FORM

        String response = duke.parseCommands(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialogue(input, userImage),
                DialogBox.getDukeDialogue(response, dukeImage)
        );
        userInput.clear();
        if(input.equals("bye")) {
            TimerTask timertask = new TimerTask() {
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            };
            Timer timer = new Timer();
            timer.schedule(timertask, 800);
        }
    }
}
//@@jellyias