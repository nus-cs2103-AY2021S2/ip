package duke.gui;

import javafx.fml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Textfield;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Vbox;
import java.io.IOException;
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
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images.Count.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images.Duke.jpg"));
    private final String startMessage = "Hi there.\n"
            + "These are the stuff you can say.\n"

    public Mainwindow(){

    }
    public void initialize(){
        this.scrollPane.vvalueProperty().bind(this,dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(startMessage,botImage));
    }
    @FXML
    private void handleUserInput(){
        String input = userInput.getText();
        String respone = duke.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input,userImage), DialogBox.getDukeDialog(response,botImage));
        userInput.clear();
    }
}
