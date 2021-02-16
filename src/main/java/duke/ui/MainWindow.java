package duke.ui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * This class controls the creation and styling of the application's main window.
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/PoliteCat.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/NeutralCat.png"));
    private final Image dukeImageInvalid = new Image(this.getClass().getResourceAsStream("/images/SkepticalCat.png"));

    /**
     * Initializes the user-interface and displays the welcome message.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());

        String welcomeMessage = "Hello! I'm Duke!\n" + "What can I do for you?";
        this.dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, this.dukeImage)
        );

        this.userInput.clear();
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Receives a <code>String</code> of user input. Then, does the following:
     * (1) Executes the actions w.r.t. to the input.
     * (2) Computes a response and display it in a dialog container.
     * (3) Determines if the application should exit. Then, exit if so.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.duke.getResponse(input);

        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                this.duke.inputIsValid(input)
                        ? DialogBox.getDukeDialog(response, this.dukeImage)
                        : DialogBox.getDukeDialogForInvalid(response, this.dukeImageInvalid)
        );
        this.userInput.clear();

        if (this.duke.isExit(input)) {
            Platform.exit();
            System.exit(0);
        }
    }
}
