package justin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import justin.Justin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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

    private Justin justin;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image justinImage = new Image(this.getClass().getResourceAsStream("/images/DaJustin.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setJustin(Justin j) {
        justin = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = justin.getResponse(input);

        if (input.equals("bye")) {
            terminate();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getJustinDialog(response, justinImage)
            );
        }

        userInput.clear();
    }

    public void getWelcomeMessage() {
        String welcome = justin.getWelcome();
        dialogContainer.getChildren().add(DialogBox.getJustinDialog(welcome, justinImage));

    }

    @FXML
    public void terminate() {
        String exit = justin.getTerminate();
        dialogContainer.getChildren().add(DialogBox.getJustinDialog(exit, justinImage));
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> System.exit(0), 5, TimeUnit.SECONDS);
    }

}