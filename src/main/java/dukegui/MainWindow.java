package dukegui;

import dukebody.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    // data members
    private Duke duke = new Duke();
    private Image userFace = new Image(this.getClass().getResourceAsStream(
            "/images/DefaultUser.png"));;

    private Image dukeFace = new Image(this.getClass().getResourceAsStream(
            "/images/DogeDuke.png"));

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(
            "eee, dd MMM yyyy HH:mm a");

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void syncDuke (Duke duke) {
        this.duke = duke;
        duke.syncWindow(this);

        userInput.setOnAction((event) -> {
            if (duke.hasSetupUser()) {
                duke.respondToCommand(pushUserInput());
            } else{
                duke.userSetup(pushUserInput());
            }
        });

        sendButton.setOnMouseClicked((event) -> {
            if (duke.hasSetupUser()) {
                duke.respondToCommand(pushUserInput());
            } else{
                duke.userSetup(pushUserInput());
            }
        });
    }

    // accessors
    public DateTimeFormatter getDateFormat () {
        return dateFormat;
    }

    // mutators
    @FXML
    private String pushUserInput () {
        String input = userInput.getText();
        dialogContainer.getChildren().add(new DialogBox(input, userFace));
        userInput.clear();
        return input;
    }

    @FXML
    public void dukeOutput (String ... messages) {
        for (String message: messages) {
            dialogContainer.getChildren().add(new DialogBox(message, dukeFace,
                    true));
        }
    }

    public void changeDateFormat (String formatExpression) {
        dateFormat = DateTimeFormatter.ofPattern(formatExpression);
    }
}
