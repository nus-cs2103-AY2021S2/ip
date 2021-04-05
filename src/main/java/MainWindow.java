import duke.Duke;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Rimuru.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Ranga(3).png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setSpacing(5);
        dialogContainer.setPadding(new Insets(5,5,5,5));
        //dialogContainer.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY,
        //        Insets.EMPTY)));
        //dialogContainer.setBackground(new Background(new BackgroundImage(new Image(
        //        this.getClass().getResourceAsStream("images/bg(2).jpg")), BackgroundRepeat.REPEAT,
        //        BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(d.startIntro(), dukeImage));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(d.startReminders(), dukeImage));
    }

    public void close() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.beginClose(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (response.contains("I'm sorry...")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeErrorDialog(response, dukeImage));
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        if (input.trim().equalsIgnoreCase("BYE")) {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished( event -> stage.close() );
            delay.play();
        }
        userInput.clear();
    }
}
