package duke.ui;


import duke.command.Command;
import duke.utils.Parser;
import duke.utils.TaskStorage;
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
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;


    private String response;

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/cat1.jpg"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat2.jpg"));

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.GREETING, dukeImage),
                DialogBox.getDukeDialog(TaskStorage.loadFiles(), dukeImage)
        );

    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        Parser parser = new Parser(userText);
        Command command = parser.parse();
        response = command.execute();
        String dukeText = response;

        if (response.equals(Ui.FAREWELL)) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            Platform.exit();
        }

        if (!response.equals("")) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(Ui.COMMAND_ERROR, dukeImage)
            );
        }



        userInput.clear();
    }



}
