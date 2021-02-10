package fakebot;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import fakebot.command.Command;
import fakebot.command.CommandException;
import fakebot.ui.DialogBox;


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


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Logo.png"));
    private Image fakeBotImage = new Image(this.getClass().getResourceAsStream("/images/wolf.png"));

    private FakeBot fakeBot;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set Fakebot for the application
     * @param fakeBot an Fakebot
     */
    public void setFakeBot(FakeBot fakeBot) {
        this.fakeBot = fakeBot;
        handleUserInput("Setup Fakebot");
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
            String outputText = fakeBot.processCommand(command);

            if (outputText.length() > 0) {
                addTextToContainer(userInput, outputText);
            } else {
                Stage stage = (Stage) this.getScene().getWindow();
                stage.close();
            }

        } catch (CommandException e) {
            addTextToContainer(userInput, e.getMessage());
        }
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text string containing text to add.
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setFont(Font.font("Courier New", 12));
        textToAdd.setWrapText(true);

        return textToAdd;
    }


    /**
     * Add text to container.
     *
     * @param text text to be add to container.
     */
    private void addTextToContainer(String text) {
        dialogContainer.getChildren().add(DialogBox.getFakebotDialog(text, fakeBotImage));
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
}
