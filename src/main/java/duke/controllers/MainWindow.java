package duke.controllers;

import java.util.List;

import duke.main.Duke;
import duke.responses.Response;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private Font monospacedFont = Font.getDefault(); // default fallback

    /**
     * Sets initial properties of components.
     */
    @FXML
    public void initialize() {
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        overrideMonospacedFont();
    }

    private void overrideMonospacedFont() {
        List<String> availableFonts = Font.getFontNames();
        String[] selectedFonts = { "Consolas", "Monaco", "Menlo", "Courier", "Courier New" };
        for (String font : selectedFonts) {
            if (availableFonts.contains(font)) {
                monospacedFont = new Font(font, 11);
                break;
            }
        }
    }

    /** Initialize Duke and queries initial setup information */
    public void setDuke(Duke d) {
        duke = d;
        updateResponseContainer(duke.getStartupResponse());
        updateTasklistContainer(duke.getTasklistString());
    }

    /**
     * Updates response, tasklist and input history containers upon receiving user input.
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
            updateResponseContainer(responseText);
            updateTasklistContainer(duke.getTasklistString());
            userInput.clear();
            break;
        default:
            assert false; // will not enter here if all branches listed
            break;
        }
    }

    private void updateInputHistoryContainer(String inputText, boolean isBadInput) {
        /* Update last text box to neutral color */
        List<Node> nodes = inputHistoryContainer.getChildren();
        if (!nodes.isEmpty()) {
            InputHistoryBox previousChild = (InputHistoryBox) nodes.get(0);
            previousChild.setInputFaded();
        }

        /* Create individual input text box */
        InputHistoryBox textBox = new InputHistoryBox(inputText, isBadInput);
        textBox.setOnMouseClicked(event -> {
            focusOnTextField();
            userInput.setText(inputText);
            userInput.setFont(monospacedFont);
            userInput.positionCaret(inputText.length());
        });

        /* Add text box to container */
        inputHistoryContainer.getChildren().add(0, textBox);
    }

    private void updateResponseContainer(String responseText) {
        responseContainer.setText(responseText);
        responseContainer.setFont(monospacedFont);
    }

    private void updateTasklistContainer(String tasklistString) {
        tasklistContainer.setText(tasklistString);
        tasklistContainer.setFont(monospacedFont);

    }

    @FXML
    private void focusOnTextField() {
        userInput.requestFocus();
    }
}
