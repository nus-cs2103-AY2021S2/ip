package quackers.ui.javafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import quackers.Quackers;
import quackers.command.CommandResponse;
import quackers.command.StatsCommand;

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

    private Quackers quackers;

    private Image quackersImage = new Image(this.getClass().getResourceAsStream("/images/Quackers.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));

    /**
     * Initialises GUI control properties.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(
                this.dialogContainer.heightProperty());
    }

    public void setQuackers(Quackers d) {
        this.quackers = d;
        this.dialogContainer.getChildren().add(
                QuackersTextDialogBox.getDialogBox(this.quackers.getGreeting(), this.quackersImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Quackers's reply.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        CommandResponse response = this.quackers.getResponse(input);
        if (response.canExit()) {
            Platform.exit();
        }

        if (!input.isBlank()) {
            this.dialogContainer.getChildren().add(UserTextDialogBox.getDialogBox(input, this.userImage));
            if (response.getCommandClass() == StatsCommand.class) {
                this.dialogContainer.getChildren().add(
                        QuackersPieChartDialogBox.getDialogBox(this.quackers.getTaskList(), this.quackersImage));
            } else {
                this.dialogContainer.getChildren().add(
                        QuackersTextDialogBox.getDialogBox(response.toString(), this.quackersImage));
            }
        }
        this.userInput.clear();
    }
}
