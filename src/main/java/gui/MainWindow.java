package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import simulator.ChatBot;
import ui.Ui;

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

    private ChatBot chatbot;


    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image chatBotImage = new Image(this.getClass().getResourceAsStream("/images/cat.png"));

    /**
     * initialize the MainWindow for ChatBot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        chatbot = new ChatBot();
    }

    /**
     * Set a MainWindow for Duke.
     */
    public void setDuke() {
        dialogContainer.getChildren().addAll(
                new Label(Ui.WELCOME_MSG + Ui.LOGO),
                new Label(chatbot.startup()),
                DialogBox.getDukeDialog(Ui.GREETING_MSG, chatBotImage)
        );

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chatbot.process(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, chatBotImage)
        );
        userInput.clear();
    }
}