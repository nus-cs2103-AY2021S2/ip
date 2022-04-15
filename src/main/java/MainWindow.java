import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pason.commands.CommandResult;
import pason.commands.CommandResultType;

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

    private Pason pason;

    private Image meImage = new Image(this.getClass().getResourceAsStream("/images/me.png"));
    private Image pasonImage = new Image(this.getClass().getResourceAsStream("/images/pason.png"));

    private Ui ui;

    /**
     * Initialises main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        ui = new Ui(dialogContainer);
        ui.showGreeting();
    }

    public void setPason(Pason p) {
        pason = p;
    }

    /**
     * Prints pason's response from Pason as well as the original command.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResult response = pason.getResponse(input);
        ui.showUserChat(input);
        ui.showPasonChat(response.getOutput(), response.getCommandResultType());
        userInput.clear();

        if (response.getCommandResultType() == CommandResultType.BYE) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 1000);
        }
    }
}
