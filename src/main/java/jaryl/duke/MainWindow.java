package jaryl.duke;

import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class MainWindow {
    private Duke duke;

    @FXML
    private ScrollPane scroller;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private VBox dialogBox;

    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/ash.png"));
    private Image dukeImg = new Image(this.getClass().getResourceAsStream("/images/pikachu.png"));

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    @FXML
    public void initialize() {
        scroller.vvalueProperty().bind(dialogBox.heightProperty());
    }

    public void textUpdateHandler() {
        if (sendButton.isDisabled() && !userInput.getText().isBlank()) {
            sendButton.setDisable(false);
        }
        if (!sendButton.isDisabled() && userInput.getText().isBlank()) {
            sendButton.setDisable(true);
        }
    }

    @FXML
    private void userInputHandler() {
        String input = userInput.getText();
        String resp = duke.getResponse(input);
        dialogBox.getChildren().addAll(
                Dialog.getUserResponse(input, userImg),
                Dialog.getDukeResponse(resp, dukeImg)
        );
        userInput.clear();
    }
}
