package switchblade;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private myList taskList;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/waving.png"));
    private final Image switchBladeImage = new Image(this.getClass().getResourceAsStream("/images/robot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTaskList(myList taskList) {
        this.taskList = taskList;

        // String to print on init
        String response = Ui.initialise() + "\n" + Ui.initRetrieveList(taskList);
        dialogContainer.getChildren().addAll(
                DialogBox.getSbDialog(response, switchBladeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = SwitchBlade.processCommand(userInput.getText(), taskList);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSbDialog(response, switchBladeImage)
        );

        if (userInput.getText().equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }

        userInput.clear();
    }
}
