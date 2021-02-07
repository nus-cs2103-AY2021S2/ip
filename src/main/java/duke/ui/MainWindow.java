package duke.ui;

import java.util.concurrent.CompletableFuture;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/mcspicy.png"));
    private Image welcomeImage = new Image(this.getClass().getResourceAsStream("/images/logo.png"));

    /**
     * Initialises MainWindow object with welcome message and logo.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        ImageView logo = new ImageView(welcomeImage);
        logo.setFitWidth(100);
        logo.setPreserveRatio(true);
        logo.setTranslateX(10);
        dialogContainer.setAlignment(Pos.BASELINE_CENTER);
        dialogContainer.getChildren().addAll(
                logo, DialogBox.getDukeDialog(Ui.welcomeString(), dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. Doesn't do anything if the user input is blank.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("")) {
            return;
        }

        String response = duke.getResponse(input);

        DialogBox userDb = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDb = DialogBox.getDukeDialog(response, dukeImage);

        HBox.setHgrow(userDb, Priority.ALWAYS);
        HBox.setHgrow(dukeDb, Priority.ALWAYS);
        dialogContainer.getChildren().addAll(
                userDb, dukeDb
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            cf.thenRun(Platform::exit).thenRun(() -> System.exit(0));
        }
    }
}
