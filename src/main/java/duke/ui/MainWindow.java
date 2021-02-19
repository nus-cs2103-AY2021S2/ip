package duke.ui;

import duke.logic.Duke;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Image dukeConfusedImage = new Image(this.getClass().getResourceAsStream("/images/DaDukeConfused.png"));

    private String dukeName = "Duke";
    private String userName = "You";

    /**
     * Initialises MainWindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the duke used by MainWindow to the given duke
     * @param d The duke to be set
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Sets the Stage MainWindow is set in
     * @param s The stage to be set
     */
    public void setStage(Stage s) {
        stage = s;
    }

    /**
     * Tells duke to greet the user
     */
    public void greet() {
        String welcome = duke.getWelcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcome, dukeImage, dukeName)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage, userName),
                DialogBox.getDukeDialog(response, getDukeImage(), dukeName)
        );
        userInput.clear();
        if (duke.isClosed()) {
            exit();
        }
    }

    private void exit() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(event -> {
            stage.close();
        });
        delay.play();
    }

    private Image getDukeImage() {
        if (duke.isConfused()) {
            return dukeConfusedImage;
        } else {
            return dukeImage;
        }
    }
}
