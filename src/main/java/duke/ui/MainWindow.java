package duke.ui;

import java.io.IOException;

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
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String LOAD_ERROR_MESSAGE = "There might be a problem with initializing the necessary"
            + " data files in data folder, PLEASE Close the program and check the data directory";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    //@author Frieda Bredesen
    //Image DaUser.png
    // https://unsplash.com/photos/IxlY2KB4Krs?utm_source=unsplash&utm_medium=referral&utm_content=creditShareLink
    //@author Mark Stoop
    // Image DaDuke.png
    //https://unsplash.com/photos/IxlY2KB4Krs?utm_source=unsplash&utm_medium=referral&utm_content=creditShareLink
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
    //@author


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke() {
        try {
            duke = new Duke();
        } catch (IOException e) {
            dialogContainer.getChildren()
                    .add(DialogBox.getDukeDialog(LOAD_ERROR_MESSAGE, dukeImage));
            return;
        }
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.startMessage(), dukeImage));
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
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (duke.getIsExit()) {
            Platform.exit();
        }
    }


}
