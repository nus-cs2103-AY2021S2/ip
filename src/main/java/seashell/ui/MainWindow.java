package seashell.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seashell.Seashell;
import seashell.exception.SeashellException;

/**
 * Controller for seashell.ui.MainWindow. Provides the layout for the other controls.
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

    private Seashell seashell;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image seashellImage = new Image(this.getClass().getResourceAsStream("/images/DaSeashell.png"));

    /**
     * Initialize scroll pane and show welcome message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getSeashellDialog(Ui.showWelcome(), seashellImage)
        );
    }

    public void setSeashell(Seashell seashell) {
        this.seashell = seashell;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws SeashellException {
        String input = userInput.getText();
        String response = seashell.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSeashellDialog(response, seashellImage)
        );
        userInput.clear();
    }
}
