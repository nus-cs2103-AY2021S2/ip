package duke.ui;

import java.io.IOException;
import java.util.function.Consumer;

import duke.commands.CommandResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends AnchorPane implements Ui {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Consumer<String> inputHandler;

    public Gui(Consumer<String> inputHandler) {
        this.inputHandler = inputHandler;
    }

    @FXML
    public void initialize() {
        userInput.setOnAction(this::handleUserInput);
        sendButton.setOnAction(this::handleUserInput);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Ying Jen: Your Personal Assistant");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput(ActionEvent event) {
        String input = userInput.getText();
        if (!isValid(input)) {
            return;
        }

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();

        inputHandler.accept(input);
    }

    @Override
    public String readCommand() {
        throw new UnsupportedOperationException("GUI uses an event-driven model!");
    }

    @Override
    public void showCommandResult(CommandResult result) {
        if (result.hasFeedback()) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(result.getFeedback(), dukeImage)
            );
        }
    }

    @Override
    public void showError(String errMsg) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(errMsg, dukeImage)
        );
    }

    @Override
    public void showFarewell() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Goodbye, cruel world!", dukeImage)
        );
    }

    @Override
    public void showGreeting() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello, I am Ying Jen.\n"
                        + "How may I help you?", dukeImage)
        );
    }

    @Override
    public void showMessage(String msg) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(msg, dukeImage)
        );
    }

    private boolean isValid(String input) {
        return !input.isBlank();
    }
}
