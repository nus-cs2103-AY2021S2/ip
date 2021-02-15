package lihua.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lihua.commands.feedback.CommandResult;
import lihua.commons.Messages;
import lihua.main.Lihua;

/**
 * Controller for lihua.ui.MainWindow. Provides the layout for the other controls.
 */
//@@author Cheng20010201
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications.
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lihua lihua;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/geisha-2-concrete.jpg"));
    private Image lihuaImage = new Image(this.getClass().getResourceAsStream("/images/geisha-concrete.jpg"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Configures the bot with entity Lihua.
     *
     * @param lihua The entity to be configured.
     */
    public void setLihua(Lihua lihua) {
        this.lihua = lihua;
        sendWelcomeMessage();
    }

    /**
     * Sends a welcome message to the user.
     */
    public void sendWelcomeMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getLihuaDialog(Messages.MESSAGE_HELLO, lihuaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Lihua's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * If the response is null, the application should exit.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResult result = lihua.getResponse(input);
        String response = result.getFeedBack();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        if (result.isUserCommandError()) {
            dialogContainer.getChildren().add(DialogBox.getLihuaDialogError(response, lihuaImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getLihuaDialog(response, lihuaImage));
        }
        userInput.clear();
    }
}
//@@author
