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
    private VBox dialogContainer;
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
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setBorder(THIN_BORDER);
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
        String response = duke.getResponse(input);
//        if (response.status == ResponseStatus.EXIT) {
//            Platform.exit();
//        }

        dialogContainer.getChildren().addAll(
                getDialogLabel(input)
        );
        responseContainer.setText(input);
//        responseFlow.getChildren().add(new Text(input));'
        tasklistContainer.setText(duke.getTasklist());
        userInput.clear();
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setPadding(new Insets(5, 10, 5, 10));
        //textToAdd.setBorder(THIN_BORDER);
        textToAdd.setFont(FONT);
        textToAdd.setWrapText(true);
        textToAdd.setOnMouseClicked(event -> { userInput.setText(text); });
        return textToAdd;
    }

    @FXML
    private void focusOnTextField() {
        userInput.requestFocus();
    }
}
