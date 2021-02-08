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

/**
 * Represents a graphical UI with a chat-bot style interface for Duke.
 *
 * @author Benedict Khoo
 */
public class Gui extends AnchorPane implements Ui {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/YingJen.jpg"));
    private final Image appIcon = new Image(this.getClass().getResourceAsStream("/images/YingJen.jpg"));
    private final Consumer<String> inputHandler;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    /**
     * Creates a new GUI with a specified callback to handle user input.
     *
     * @param inputHandler The callback to execute when a user input is received.
     */
    public Gui(Consumer<String> inputHandler) {
        this.inputHandler = inputHandler;
    }

    /**
     * Initializes the GUI elements; called by JavaFX
     */
    @FXML
    public void initialize() {
        userInput.setOnAction(this::handleUserInput);
        sendButton.setOnAction(this::handleUserInput);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * NOT SUPPORTED by this GUI implementation. This uses an event-driven model.
     *
     * @throws UnsupportedOperationException When called.
     * @#return Never. It always throws an exception.
     */
    @Override
    public String readCommand() {
        throw new UnsupportedOperationException("GUI uses an event-driven model!");
    }

    /**
     * Displays the given result as a 'chat message' from Duke.
     *
     * @param result The result to display.
     */
    @Override
    public void showCommandResult(CommandResult result) {
        if (result.hasFeedback()) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(result.getFeedback(), dukeImage)
            );
        }
    }

    /**
     * Displays the error message as a 'chat message' from Duke.
     *
     * @param errMsg The error message to display.
     */
    @Override
    public void showError(String errMsg) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(errMsg, dukeImage)
        );
    }

    /**
     * Displays a farewell message as a 'chat message' from Duke.
     */
    @Override
    public void showFarewell() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Goodbye, cruel world!", dukeImage)
        );
    }

    /**
     * Displays a welcome message as a 'chat message' from Duke.
     */
    @Override
    public void showGreeting() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello, I am Ying Jen.\n"
                        + "How may I help you?", dukeImage)
        );
    }

    /**
     * Displays the given message as a 'chat message' from Duke.
     *
     * @param msg The message to display.
     */
    @Override
    public void showMessage(String msg) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(msg, dukeImage)
        );
    }

    /**
     * Sets up the JavaFX structure for the GUI.
     *
     * @param stage The JavaFX stage to use.
     */
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Ying Jen: Your Personal Assistant");
            stage.getIcons().add(appIcon);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUserInput(ActionEvent event) {
        String input = userInput.getText();
        if (!isValid(input)) {
            return;
        }

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();

        inputHandler.accept(null);
    }

    private boolean isValid(String input) {
        return !input.isBlank();
    }
}
