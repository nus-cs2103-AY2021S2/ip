package yoda.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import yoda.Yoda;

/**
 * MainWindow class which acts as the base for the gui aspects.
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

    private Yoda yoda;

    private Image userImage;
    private Image yodaImage;

    /**
     * Initializes the images of user and Yoda and sets up the scroll pane.
     */
    @FXML
    public void initialize() {
        userImage = new Image(this.getClass().getResourceAsStream("/images/R2-D2.png"));
        yodaImage = new Image(this.getClass().getResourceAsStream("/images/Yoda.png"));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets Yoda to MainWindow
     * @param yoda Yoda to be set.
     */
    public void setYoda(Yoda yoda) {
        this.yoda = yoda;
    }

    /**
     * Greets user upon opening the application.
     */
    public void greetUser() {
        String greetings = yoda.greetUser();
        DialogBox greetingDialogBox = DialogBox.getYodaDialog(greetings, yodaImage);
        dialogContainer.getChildren().add(greetingDialogBox);
    }

    /**
     * Terminates the application if exit command is given.
     */
    public void checkTermination(String input) {
        if (input.equalsIgnoreCase("bye")) {
            PauseTransition exit = new PauseTransition(Duration.seconds(3));
            exit.setOnFinished(event -> Platform.exit());
            exit.play();
        }
    }

    /**
     * Handles user input and exits when user is done.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yoda.runYoda(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYodaDialog(response, yodaImage)
        );
        userInput.clear();
        checkTermination(input);
    }
}
