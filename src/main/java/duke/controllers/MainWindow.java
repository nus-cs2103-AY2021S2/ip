package duke.controllers;

import duke.Duke;
import duke.ui.JavafxUi;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    public static final int DELAY_BEFORE_CLOSE = 3000;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
        Duke.Response response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog("Da: " + input, userImage),
            DialogBox.getDukeDialog("Duke: " + response.response, dukeImage)
        );
        userInput.clear();

        if (response.isExit) {
            // Closes dialog in 3 seconds (after updating GUI)
            new Thread(() -> {
                try {
                    Thread.sleep(DELAY_BEFORE_CLOSE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.exit();
            }).start();
        }
    }

    /**
     * Show introduction for Duke
     */
    public void showIntro() {
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog("Duke: " + JavafxUi.INTRO_MESSAGE, dukeImage)
        );
    }

}
