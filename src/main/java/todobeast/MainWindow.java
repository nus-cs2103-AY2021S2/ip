package todobeast;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    private ToDoBeast toDoBeast;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image todobeast = new Image(this.getClass().getResourceAsStream("/images/todobeast.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    public void getWelcome() {
        String welcome = toDoBeast.getWelcome();
        dialogContainer.getChildren().add(DialogBox.getToDoBeastDialog(welcome, todobeast));
    }

    public void setToDoBeast(ToDoBeast t) {
        toDoBeast = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = toDoBeast.getResponse(input);
        if (input.equals("exit")) {
            handleExit();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, user),
                    DialogBox.getToDoBeastDialog(response, todobeast)
            );
            userInput.clear();
        }

    }

    @FXML
    private void handleExit() {
        String exit = toDoBeast.getExit();
        dialogContainer.getChildren().add(DialogBox.getToDoBeastDialog(exit, todobeast));
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> System.exit(0), 3, TimeUnit.SECONDS);
    }
}
