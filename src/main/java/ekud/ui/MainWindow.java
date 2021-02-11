package ekud.ui;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import ekud.Ekud;
import ekud.common.core.Messages;
import ekud.common.exception.EkudException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The main content of the GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogueContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ekud ekud;

    /**
     * Construct the main chat window.
     */
    public MainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setControllerFactory(param -> this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start-up procedures for the MainWindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
        dialogueContainer.getChildren().add(new EkudDialogueBox(Messages.WELCOME_MESSAGE));
    }

    /**
     * Set the instance of Ekud that is used.
     *
     * @param e The Ekud instance.
     */
    public void setEkud(Ekud e) {
        ekud = e;
    }

    @FXML
    private void handleUserInput() {
        String fullCommand = userInput.getText().trim();
        userInput.clear();

        /* ignore any blank input */
        if (fullCommand.isEmpty()) {
            return;
        }

        dialogueContainer.getChildren().add(new UserDialogueBox(fullCommand));

        try {
            String reply = ekud.getResponse(fullCommand).trim();
            dialogueContainer.getChildren().add(new EkudDialogueBox(reply));
        } catch (EkudException e) {
            dialogueContainer.getChildren().add(new EkudDialogueBox(e.toString()));
        }

        if (!ekud.isOnline()) {
            // block input
            userInput.setDisable(true);
            sendButton.setDisable(true);

            // quit the application
            Thread exitThread = new Thread(this::countDownAndQuit);
            exitThread.start();
        }
    }

    /**
     * Method that will quit the application after 3 seconds, useful for spawning another thread to not interrupt the
     * main execution thread.
     */
    private void countDownAndQuit() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Platform.exit();
    }
}
