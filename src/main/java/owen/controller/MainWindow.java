package owen.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import owen.Chatbot;

/**
 * Controller for main GUI interface window.
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

    private Chatbot bot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Cat.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/Owl.jpg"));

    /**
     * Creates controller for main GUI interface window.
     *
     * @param bot Chatbot that GUI acts as an interface for.
     */
    public MainWindow(Chatbot bot) {
        this.bot = bot;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes main GUI interface window layout and get hello message from chatbot.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());

        String response = this.bot.getResponse();
        this.dialogContainer.getChildren().addAll(
                DialogBox.getBotDialog(response, this.botImage),
                new Separator());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing bot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        this.userInput.clear();

        this.bot = this.bot.parseCommand(input);
        String response = this.bot.getResponse();

        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                new Separator(),
                DialogBox.getBotDialog(response, this.botImage),
                new Separator());

        if (!this.bot.isRunning()) {
            Platform.exit();
        }
    }
}
