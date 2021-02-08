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
public class DukeUi extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(printGreetings(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            System.exit(0);
        }
    }

    /**
     * Prints the greeting message.
     */
    public String printGreetings() {
        String logo = "$$    $$                                $$\n"
                    + "$$    $$                                $$\n"
                    + "$$    $$   $$$$$$   $$$$$$$    $$$$$$$  $$$$$$$   $$$$$$ $$$$    $$$$$$   $$$$$$$\n"
                    + "$$$$$$$$  $$    $$  $$    $$  $$        $$    $$  $$   $$   $$        $$  $$    $$\n"
                    + "$$    $$  $$$$$$$$  $$    $$  $$        $$    $$  $$   $$   $$   $$$$$$$  $$    $$\n"
                    + "$$    $$  $$        $$    $$  $$        $$    $$  $$   $$   $$  $$    $$  $$    $$\n"
                    + "$$    $$   $$$$$$$  $$    $$   $$$$$$$  $$    $$  $$   $$   $$   $$$$$$$  $$    $$\n";

        return "Greetings Boss, good to see you.\n" + "What can I do for you?\n";
    }

    public String printInputPrompt() {
        return "Hit me up boss.";
    }

    public String printCommand(Command command) {
        return command.toDukeOutput();
    }

    public String printTask(Task task) {
        return task.toString();
    }

    /**
     * Prints the error message of the input exception.
     *
     * @param e The caught error to be printed.
     */
    public String printErrorMessage(Exception e) {
        return "Sorry boss something went wrong:\n" + e.getMessage();
    }
}