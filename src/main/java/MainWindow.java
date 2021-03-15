import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private DialogBox introduce = DialogBox.getDialog(Ui.introduce(), dukeImage, false);
    //dialogContainer.getChildren().add(introduce);
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(introduce);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void setStage(Stage s) {
        stage = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getDialog(input, userImage, true),
                DialogBox.getDialog(response, dukeImage, false)
        );
        userInput.clear();
        if (input.equals("bye")) {
            //how to make it show exit message
            exitWindow();
        }
    }

    private void exitWindow() {
        int secondsToSleep = 1;
        try {
            Thread.sleep(secondsToSleep * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        stage.close();
    }
}