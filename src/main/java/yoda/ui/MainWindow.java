package yoda.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import yoda.Yoda;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Yoda yoda;

    private Image userImage;
    private Image yodaImage;

    @FXML
    public void initialize() {
        userImage = new Image(this.getClass().getResourceAsStream("/images/R2-D2.png"));
        yodaImage = new Image(this.getClass().getResourceAsStream("/images/Yoda.png"));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setYoda(Yoda yoda) {
        this.yoda = yoda;
    }

    public void greetUser() {
        String greetings = yoda.greetUser();
        DialogBox greetingDialogBox = DialogBox.getYodaDialog(greetings, yodaImage);
        dialogContainer.getChildren().add(greetingDialogBox);
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yoda.runYoda(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYodaDialog(response, yodaImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
}
