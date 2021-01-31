import duke.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaSquirrel.jpg"));
//    private Image treeImage = new Image(this.getClass().getResourceAsStream("/images/DaTree.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayDukeMessage(Ui.WELCOME_MESSAGE);

//        // new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
//        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
//        // new BackgroundImage(image, repeatX, repeatY, position, size)
//        BackgroundImage backgroundImage = new BackgroundImage(treeImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
//        // new Background(images...)
//        Background background = new Background(backgroundImage);
//        anchorPane.setBackground(background);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private void displayDukeMessage(String response) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();

        if (!input.isEmpty()) {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
            );

            userInput.clear();
        }
    }
}
