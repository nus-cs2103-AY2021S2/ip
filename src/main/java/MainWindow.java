import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final Font FONT = new Font("Consolas", 11);
    private static final Border THIN_BORDER = new Border(new BorderStroke(
            Paint.valueOf("0x000000"),
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(1)
    ));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox inputHistoryContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private TextArea responseContainer;
    @FXML
    private TextArea tasklistContainer;

    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(inputHistoryContainer.heightProperty());
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

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
        Response response = duke.getResponse(input);
        boolean isGoodInput = true;
        switch (response.getStatus()) {
        case EXIT:
            Platform.exit();
            break; // program termination, no fall-through
        case BAD:
            isGoodInput = false; // fallthrough
        case OK:
            String responseText = response.getMessage();
            inputHistoryContainer.getChildren().add(getInputBox(input));
            responseContainer.setText(responseText);
            tasklistContainer.setText(duke.getTasklist());
            userInput.clear();
            break;
        default:
            assert false; // will not enter here if all branches listed
            break;
        }
    }

    private InputHistoryBox getInputBox(String text) {
        InputHistoryBox textBox = InputHistoryBox.getBox(text);
        textBox.setOnMouseClicked(event -> {
            focusOnTextField();
            userInput.setText(text);
            userInput.positionCaret(text.length());
        });
        return textBox;
    }

    @FXML
    private void focusOnTextField() {
        userInput.requestFocus();
    }
}
