import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends GridPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Runs Duke and access storage.
     *
     * @param d Duke object used.
     */
    public void setDuke(Duke d) {
        duke = d;
        try {
            duke.storage.loadData();
        } catch (FileNotFoundException e) {
            System.out.println("File not found :(");
        }
    }

    /**
     * Greets the user and prints out list in storage.
     */
    public void greetings() {
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog("Welcome to Duke! \n" + duke.parser.printList(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input, duke);
        duke.storage.writeTaskList();
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }


}
