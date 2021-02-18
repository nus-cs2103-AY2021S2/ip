package bearbear.ui;

import java.io.IOException;

import bearbear.bearbear.BearBear;
import bearbear.bearbear.Main;
import bearbear.command.Command;
import bearbear.exceptions.InvalidArgumentException;
import bearbear.exceptions.InvalidCommandException;
import bearbear.parser.Parser;
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
    private BearBear bot;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Pigglet.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Pooh.png"));

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
        assert welcomeMessage != null : "Welcome message should not be empty!";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    /**
     * Sets Duke object.
     * @param duke A Duke object.
     */
    public void setBearBear(BearBear duke) {
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
            assert userCommand != null : "User command should not be empty at this point!";
            response = Main.runUserCommand(userCommand, bot);
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
}
