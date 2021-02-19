package kobe;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// MainWindow class (below) and FXML files adapted from https://se-education.org/guides/tutorials/javaFxPart4.html

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

    private kobe.KobeN kobe;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Shibaban action.png"));
    private Image kobeImage = new Image(this.getClass().getResourceAsStream("/images/Loppie says hi.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKobe(kobe.KobeN k) {
        kobe = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public void handleUserInput() {
        String input = userInput.getText();
        String response = kobe.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKobeDialog(response, kobeImage)
        );
        userInput.clear();
    }

}
