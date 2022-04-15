package kelbot;

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
    private Kelbot kelbot;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image kelbotImage = new Image(this.getClass().getResourceAsStream("/images/Kelbot.jpg"));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    public void setKelbot(Kelbot kelbot) {
        this.kelbot = kelbot;
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Kelbot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kelbot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKelbotDialog(response, kelbotImage)
        );
        userInput.clear();
    }
    /**
     * On init, add a DialogBox that recaps what the user already has on their list.
     * @param taskList The tasklist to be printed.
     */
    @FXML void init(String taskList) {
        String toPrint = "";
        if (taskList.equals("")) {
            toPrint = "Welcome to Kelbot, I can help you track your tasks!";
        } else {
            toPrint = "Here are your tasks from your last usage!\n" + taskList;
        }
        dialogContainer.getChildren().add(DialogBox.getKelbotDialog(toPrint, kelbotImage));
    }
}
