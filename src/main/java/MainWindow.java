import duke.responses.Response;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

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
        responseContainer.setText(duke.getStartupResponse());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duke.getResponse(input);
        boolean isBadInput = false;
        switch (response.getStatus()) {
        case EXIT:
            Platform.exit();
            break; // program termination, no fall-through
        case BAD:
            isBadInput = true; // fallthrough
        case OK:
            String responseText = response.getMessage();
            updateInputHistoryContainer(input, isBadInput);
            responseContainer.setText(responseText);
            tasklistContainer.setText(duke.getTasklist());
            userInput.clear();
            break;
        default:
            assert false; // will not enter here if all branches listed
            break;
        }
    }

    private void updateInputHistoryContainer(String inputText, boolean isBadInput) {

        /* Update last text box to neutral color */
        int containerSize = inputHistoryContainer.getChildrenUnmodifiable().size();
        if (containerSize > 0) {
            InputHistoryBox lastChild = (InputHistoryBox) inputHistoryContainer.getChildren().get(containerSize - 1);
            lastChild.setInputFaded();
        }

        /* Create individual input text box */
        InputHistoryBox textBox = new InputHistoryBox(inputText, isBadInput);
        textBox.setOnMouseClicked(event -> {
            focusOnTextField();
            userInput.setText(inputText);
            userInput.positionCaret(inputText.length());
        });

        /* Add text box to container */
        inputHistoryContainer.getChildren().add(textBox);
    }

    private InputHistoryBox getInputBox(String text, boolean isBadInput) {
        InputHistoryBox textBox = new InputHistoryBox(text, isBadInput);
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
