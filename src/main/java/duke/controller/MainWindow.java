package duke.controller;

import duke.Duke;
import duke.DukeResponse;
import duke.component.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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

    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image DUKE_IMAGE = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private final long EXIT_DELAY_MILLI = 2000;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(new Ui().showWelcome(), DUKE_IMAGE));
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
        DukeResponse response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getDukeDialog(response.getResponse(), DUKE_IMAGE)
        );
        userInput.clear();

        //@@author stein414-reused
        //Solution adapted from https://github.com/stein414/ip
        if (response.getIsExit()) {
            CompletableFuture.delayedExecutor(EXIT_DELAY_MILLI, TimeUnit.MILLISECONDS)
                    .execute(() -> Platform.exit());
        }
    }
}
