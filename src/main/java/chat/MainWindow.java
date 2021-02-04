package chat; 

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


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
    
    private Chat chat;
    
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    /**
     * Initialises anchor pane such that initial greeting from Chat will also be displayed
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        
        //initial greeting / loading error will be displayed on start up
        dialogContainer.getChildren().addAll(
                DialogBox.getChatDialog(chat.getUi().getRespondString(), chat.getUi().getChatImage())
        );

    }

    /**
     * Initialises MainWindow object.
     * 
     * @param chat Chat the Cat.
     */
    public MainWindow(Chat chat) {
        this.chat = chat;
    }

    /**
     * Handles user input by taking in input and then giving a response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        chat.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), userImage),
                DialogBox.getChatDialog(chat.getUi().getRespondString(), chat.getUi().getChatImage())
        );
        userInput.clear();
    }
    
}