package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * The Duke program implements an application that
 * allows users to store Tasks in a list, which will
 * be saved in between sessions.
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private final TaskList taskList;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Class constructor.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
        this.taskList = storage.readFromFile();
    }

    /**
     * This method executes the main logic of the Duke program
     * and returns the message to be printed.
     *
     * @param input user input.
     * @return message to be printed.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command command = parser.parseCommand(input);
            switch (command) {
            case TODO:
                ToDo toDo = parser.parseToDo(input);
                taskList.addTask(toDo);
                storage.writeToFile(taskList);
                response = ui.getTaskAddedConfirmation(toDo, taskList);
                break;
            case DEADLINE:
                Deadline deadline = parser.parseDeadline(input);
                taskList.addTask(deadline);
                storage.writeToFile(taskList);
                response = ui.getTaskAddedConfirmation(deadline, taskList);
                break;
            case EVENT:
                Event event = parser.parseEvent(input);
                taskList.addTask(event);
                storage.writeToFile(taskList);
                response = ui.getTaskAddedConfirmation(event, taskList);
                break;
            case LIST:
                response = ui.getAllTasks(taskList);
                break;
            case FIND:
                String keyword = parser.parseKeyword(input);
                TaskList matchingTasks = taskList.findMatchingTasks(keyword);
                response = ui.getMatchingTasks(matchingTasks);
                break;
            case DONE:
                int doneIndex = parser.parseDone(input, taskList);
                Task doneTask = taskList.getTask(doneIndex);
                taskList.markTaskAsDone(doneIndex);
                storage.writeToFile(taskList);
                response = ui.getTaskDoneConfirmation(doneTask);
                break;
            case DELETE:
                int deleteIndex = parser.parseDelete(input, taskList);
                Task deleteTask = taskList.getTask(deleteIndex);
                taskList.deleteTask(deleteIndex);
                storage.writeToFile(taskList);
                response = ui.getTaskDeleteConfirmation(taskList, deleteTask);
                break;
            case BYE:
                response = ui.getByeMessage();
                System.exit(0);
                break;
            default:
                response = "unreachable statement";
            }
        } catch (DukeException dukeException) {
            response = ui.getDukeExceptionMessage(dukeException);
        }
        return response;
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }
}
