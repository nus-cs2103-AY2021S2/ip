package duke.gui;

import duke.Duke;
import duke.util.Task;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
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

    private History history = new History();

    //Icon made by Freepik from www.flaticon.com
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Initialize GUI properties.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setOnKeyPressed(e -> handleUserKey(e.getCode()));
        listView.setCellFactory(list -> new CustomListCell());
    }

    /**
     * Initialise duke and add tasklist to listView.
     *
     * @param d Duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
        listView.setItems(duke.getTaskList().getList());
    }

    /**
     * Display greeting message on GUI.
     */
    public void showGreetings() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.displayGreetings(), dukeImage));
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

        history.add(input);

        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();

        if (response.equals("shutdownConfirm")) {
            Platform.exit();
        }
    }

    private void handleUserKey(KeyCode key) {
        switch(key) {
        case UP:
            userInput.setText(history.getPrevious());
            userInput.end();
            break;
        case DOWN:
            userInput.setText(history.getNext());
            userInput.end();
            break;
        case ESCAPE:
            userInput.clear();
            break;
        default:
            break;
        }
    }
}
