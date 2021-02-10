package duke;

import duke.util.Task;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

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

    //Icon made by Freepik from www.flaticon.com
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Initialize GUI properties.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Add number to listView and change color
        listView.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : (getIndex() + 1) + ". " + item);
                setStyle("-fx-text-fill:white;-fx-background-color:"
                        + (getIndex() % 2 == 0
                        ? "rgb(140, 140, 140);"
                        : "rgb(130, 130, 130);"));
            }
        });
    }

    /**
     * Initialise duke and add tasklist to listView.
     *
     * @param d Duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
        listView.setItems(duke.getTaskList());
    }

    /**
     * Display greeting message on GUI.
     */
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
        String input = userInput.getText().strip();

        if (input.isEmpty()) {
            return;
        }

        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (response.equals("shutdownConfirm")) {
            Platform.exit();
        }
    }
}
