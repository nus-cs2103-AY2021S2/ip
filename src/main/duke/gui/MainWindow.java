package duke.gui;

import javafx.fml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Textfield;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Vbox;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import duke.Duke;
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
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images.User.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images.Bot.jpg"));
    private final String startMessage = "Hi there.\n"
            + "These are the stuff you can say.\n";

    public MainWindow(){
    @FXML
    public void initialize(){
        this.scrollPane.vvalueProperty().bind(this,dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(startMessage,botImage));
    }
    public void makeDuke(Duke d){
        duke = d;
        }
    @FXML
    private void handleUserInput() {
            String input = userInput.getText();
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage), DialogBox.getDukeDialog(response, botImage));
        };
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
