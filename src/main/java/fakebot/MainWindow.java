package fakebot;


import fakebot.command.Command;
import fakebot.command.CommandException;
import fakebot.command.CommandType;
import fakebot.ui.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



//Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html

/**
 * Ui Class use for reading and printing data.
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


    //Image from : https://www.shareicon.net/man-user-profile-avatar-social-829459
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    //Image from : https://icon-library.com/icon/robotics-icon-2.html
    private Image fakeBotImage = new Image(this.getClass().getResourceAsStream("/images/fakebot.png"));
    private Image sendImage = new Image(this.getClass().getResourceAsStream("/images/send.png"));

    private FakeBot fakeBot;

    /**
     * Initialize MainWindow FXML
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        //Setup send button
        ImageView imageView = new ImageView(sendImage);
        imageView.setFitHeight(35);
        imageView.setFitWidth(60);
        sendButton.setGraphic(imageView);

    }

    /**
     * Set Fakebot for the application
     *
     * @param fakeBot an Fakebot
     */
    public void setFakeBot(FakeBot fakeBot) {
        this.fakeBot = fakeBot;
        addTextToContainer(fakeBot.getHelloMessage());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleSendButton() {
        handleUserInput(userInput.getText());
        userInput.clear();
    }

    /**
     * Handle user input.
     *
     * @param userInput yser input string.
     */
    private void handleUserInput(String userInput) {
        Command command;
        try {
            command = Parser.parseUserInputToCommand(userInput);

            //Exit when bye command detected
            if (command.getCommand() == CommandType.BYE) {
                Stage stage = (Stage) sendButton.getScene().getWindow();
                stage.close();
            }

            String outputText = fakeBot.processCommand(command);
            addTextToContainer(userInput, outputText);

        } catch (CommandException e) {
            addAlertTextToContainer(userInput, e.getMessage());
        }
    }

    /**
     * Add alert text to container.
     *
     * @param inputText inputText to be add to container.
     */
    private void addTextToContainer(String inputText) {
        dialogContainer.getChildren().add(
                DialogBox.getFakebotDialog(inputText, fakeBotImage)
        );
    }

    /**
     * Add text to container.
     *
     * @param inputText  inputText to be add to container.
     * @param outputText outputText to be add to container.
     */
    private void addTextToContainer(String inputText, String outputText) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(inputText, userImage),
                DialogBox.getFakebotDialog(outputText, fakeBotImage)
        );
    }

    /**
     * Add alert text to container.
     *
     * @param inputText  inputText to be add to container.
     * @param outputText outputText to be add to container.
     */
    private void addAlertTextToContainer(String inputText, String outputText) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(inputText, userImage),
                DialogBox.getAlertDialog(outputText, fakeBotImage)
        );
    }
}
