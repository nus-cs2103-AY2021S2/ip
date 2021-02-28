package main.duke.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import main.duke.Duke;

/**
 * Controller of the MainWindow
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

    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));
    private final String startMessage = "Hello, I am duke bot.\n"
            + "If you require any assistance with what I accept use my command help\n";

    public MainWindow(){ }

    /**
     * Initialize the MainWindow with a startMessage
     */
    @FXML
    public void initialize(){
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getUserDialog(startMessage,botImage));
    }
    public void makeDuke(Duke d){
        duke = d;
        }

    /**
     * Makes two dialogBoxes, one which echos the user input and the other contains the reply.
     * Both are appended to the dialog container and cleared after.
     */
    @FXML
    private void handleUserInput() {
            String input = userInput.getText().trim();
            String response = duke.getReply(input);
            dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBotDialog(response, botImage));
        userInput.clear();
        CompletableFuture.runAsync(() -> {
            try{
                if(input.equals("bye")){
                    Thread.sleep(3000);
                    System.exit(0);
                }
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.exit(1);
            }
                }
                );
        assert (userInput.getText().equals(""));
    }
}
