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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/fellaini.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/lukaku.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialogue(Duke.greeting(), dukeImage),
                DialogBox.getDukeDialogue(Duke.help(), dukeImage));

    }

    public void setDuke(Duke d) {
        duke = d;
    }

    //@@ banchiang-reused
    //Reused from https://github.com/banchiang/ip/blob/master/src/main/java/duke/MainWindow.java
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws Exception {
        String input = userInput.getText();
        String response = duke.getResponse(input);
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
    //@@ banchiang
}