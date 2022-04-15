import javafx.fxml.FXML;
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
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private ScrollPane scrollPane;

    private Duke duke;
    //Image below adapted from https://line.17qq.com/article/swsarucrx.html
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/image/2.png"));
    //Image below adapted from https://weheartit.com/entry/106756123
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/image/3.png"));

    public void setDuke(Duke d) {
        duke = d;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String response = duke.getResponse(input);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();
    }
}
