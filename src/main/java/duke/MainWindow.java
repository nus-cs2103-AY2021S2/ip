package duke;

import duke.util.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends SplitPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private ListView<Task> listView;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        listView.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> list) {
                return new ListCell<>() {
                    @Override protected void updateItem(Task item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item == null ? "" : (this.getIndex() + 1) + ". " + item.toString());
                    }
                };
            }
        });
    }

    public void setDuke(Duke d) {
        duke = d;
        listView.setItems(duke.getTaskList());
    }

    public void showGreetings() {
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(duke.displayGreetings(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (response.equals("shutdownConfirm")) {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }
}
