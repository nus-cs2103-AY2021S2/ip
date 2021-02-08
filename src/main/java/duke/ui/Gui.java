package duke.ui;

import java.io.IOException;
import java.util.function.Consumer;

import duke.commands.CommandResult;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private static final String MAIN_WINDOW_FXML_PATH = "/view/MainWindow.fxml";
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
        EventHandler<ActionEvent> handleUserInput = event -> {
            String input = userInput.getText();
            if (!isValid(input)) {
                return;
            }

            addUserDialogBox(input);
            userInput.clear();

            inputHandler.accept(input);
        };

        userInput.setOnAction(handleUserInput);
        sendButton.setOnAction(handleUserInput);
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
        if (!result.hasFeedback()) {
            return;
        }

        addDukeDialogBox(result.getFeedback());
    }

    /**
     * Displays the error message as a 'chat message' from Duke.
     *
     * @param errMsg The error message to display.
     */
    @Override
    public void showError(String errMsg) {
        addDukeDialogBox(errMsg);
    }

    /**
     * Displays a farewell message as a 'chat message' from Duke.
     */
    @Override
    public void showFarewell() {
        addDukeDialogBox("Goodbye, cruel world!");
    }

    /**
     * Displays a welcome message as a 'chat message' from Duke.
     */
    @Override
    public void showGreeting() {
        addDukeDialogBox("Hello, I am Ying Jen.\n"
                + "How may I help you?");
    }

    /**
     * Displays the given message as a 'chat message' from Duke.
     *
     * @param msg The message to display.
     */
    @Override
    public void showMessage(String msg) {
        addDukeDialogBox(msg);
    }

    /**
     * Sets up the JavaFX structure for the GUI.
     *
     * @param stage The JavaFX stage to use.
     */
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource(MAIN_WINDOW_FXML_PATH));
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

    private void addDukeDialogBox(String msg) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(msg, dukeImage)
        );
    }

    private void addUserDialogBox(String msg) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(msg, userImage)
        );
    }

    private boolean isValid(String input) {
        return !input.isBlank();
    }
}
