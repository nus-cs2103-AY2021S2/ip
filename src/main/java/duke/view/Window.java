package duke.view;

import duke.Duke;
import duke.model.task.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class Window extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;
    private Image buttonImage = new Image(this.getClass().getResourceAsStream("/images/button.png"));
    private ImageView imageView = new ImageView(buttonImage);
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/robot.png"));

    @FXML
    private Button sendButton = new Button("Send", imageView);

    private TaskList tasks;

    /**
     * initialise new <code>tasklist</code> and set the <code>scrollPane</code> property to work properly for GUI
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        tasks = new TaskList();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Greetings, how may I help you?", dukeImage)
        );
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
        duke.getUiController().updateUiDialog(input, this);
    }

    public void updateDialogContainer(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (response == "Bye. Hope to see you again soon!") {
            this.userInput.setVisible(false);
            this.sendButton.setVisible(false);
        }
    }
}
