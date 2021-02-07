package ui.controllers;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import duke.Duke;
import duke.DukeResponse;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for ui.controllers.MainWindow. Provides the layout for the other
 * controls.
 */
public class MainWindow extends AnchorPane {
    private static final long EXIT_DELAY_MILLISECONDS = 2000;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private ObservableList<Node> dialogs;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises fxml components
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogs = dialogContainer.getChildren();
    }

    /**
     * Initialises duke and show gretting message
     *
     * @param duke
     */
    public void initialiseDuke(Duke duke) {
        this.duke = duke;
        addDialog(duke.getGreetingMessage(), DialogUser.DUKE);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container. Clears the user
     * input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        addDialog(input, DialogUser.USER);

        userInput.clear();

        DukeResponse response = getDukeReponse(input);
        addDialog(response.getMessage(), DialogUser.DUKE);

        if (response != null && response.isExit()) {
            CompletableFuture.delayedExecutor(EXIT_DELAY_MILLISECONDS, TimeUnit.MILLISECONDS)
                    .execute(() -> Platform.exit());
        }
    }

    private DukeResponse getDukeReponse(String input) {
        DukeResponse response = null;
        try {
            response = duke.getResponse(input);
        } catch (IOException ioe) {
            response = new DukeResponse("Failed to save. Please restart the program.");
        }

        return response;
    }

    /**
     * Adds a dialog with the given message for the specified dialogUser
     *
     * @param message
     * @param dialogUser
     */
    private void addDialog(String message, DialogUser dialogUser) {
        switch (dialogUser) {
        case USER:
            dialogs.add(DialogBox.getUserDialog(message, userImage));
            break;
        case DUKE:
            dialogs.add(DialogBox.getDukeDialog(message, dukeImage));
            break;
        default:
            throw new RuntimeException("Unknown DialogUser specified");
        }
    }
}

/**
 * Enum to determine which user the dialog belongs to
 */
enum DialogUser {
    USER, DUKE
}
