package duke.ui;

import java.io.IOException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.duke.Duke;
import duke.duke.Main;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidCommandException;
import duke.parser.Parser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * The Main Window. Provides the basic application layout.
 */
public class MainWindow extends VBox {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private HBox hBox;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke bot;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/heartPiglet.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/sparklePooh.png"));

    /**
     * Initializes the required components.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        setBackgroundColor();
    }

    /**
     * Adds the welcome message label.
     */
    @FXML
    public void showWelcomeMessage() {
        String welcomeMessage = getResponse(Ui.showWelcomeMessage(bot));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    /**
     * Sets Duke object.
     * @param duke A Duke object.
     */
    public void setDuke(Duke duke) {
        bot = duke;
    }


    /**
     * Sets background colour of user interface.
     */
    public void setBackgroundColor() {
        dialogContainer.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setStyle("-fx-background: #f8f8ff; -fx-border-color: #f8f8ff;");
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String input = userInput.getText();
        String response;
        Command userCommand;
        try {
            userCommand = Parser.processInput(input, bot);
            response = Main.runUserCommand(userCommand, bot);
            if (userCommand instanceof ByeCommand) {
                exit();
            }
        } catch (InvalidCommandException | InvalidArgumentException | IOException ex) {
            response = ex.getMessage();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return input;
    }

    private void exit() {
        String exitMessage = getResponse(Ui.showExitMessage());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(exitMessage, dukeImage)
        );
        Platform.exit();
    }
}
