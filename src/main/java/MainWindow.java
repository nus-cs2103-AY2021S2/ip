import javafx.application.Platform;
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
//@@author JulietTeoh
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat2_png.png"));
    private Image dukeNormalImage = new Image(this.getClass().getResourceAsStream("/images/cat3_png.png"));
    private Image dukeErrorImage = new Image(this.getClass().getResourceAsStream("/images/cat1_png.png"));

    /**
     * Initializes the javafx frame
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(
                        "Hello! I'm Duke!\n" + "What can I do for you?", dukeNormalImage)
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
        if (!duke.getIsTerminated()) {
            String input = userInput.getText();
            String response = duke.getResponse(input);

            if (!duke.getIsCurrentError()) {

                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog(response, dukeNormalImage)
                );
            } else {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog(response, dukeErrorImage)
                );
            }
            userInput.clear();
        } else {
            if (userInput.getText().equals("q")) {
                Platform.exit();
            }
        }
    }
}
