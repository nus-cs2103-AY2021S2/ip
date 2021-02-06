import java.io.IOException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.IncompleteInputException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.utils.Command;

/**
 * Main class of the application.
 */
public class Duke {
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Creates a new instance of Duke.
     *
     * @param filePath The save file path.
     */
    public Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);
        taskList = new TaskList();
        loadData();
    }

    protected String getResponse(String input) {
        try {
            String[] tokens = Parser.splitIntoSubstrings(input);
            Command command = Parser.parseCommand(tokens);
            return runUserCommand(command, tokens);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Loads data from save file.
     */
    public void loadData() throws DukeException {
        try {
            ArrayList<Task> tasks = storage.load();
            taskList.setTaskList(tasks);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves data to save file.
     */
    public void saveData() throws DukeException {
        storage.save(taskList.getTasks());
    }

    /**
     * Processes input after it is parsed by the parser.
     *
     * @param command Command that is to be executed.
     * @param tokens  Input String split into tokens.
     * @throws DukeException If command cannot be executed.
     */
    public String runUserCommand(Command command, String[] tokens) throws DukeException {
        String message = null;
        switch (command) {
        case BYE:
            message = Ui.GOODBYE_MESSAGE;
            System.exit(0);
            break;
        case DONE:
            try {
                Task task = taskList.markAsDone(Integer.parseInt(tokens[1]) - 1);
                message = Ui.getSuccessfullyDoneMessage(task);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new IncompleteInputException(command);
            }
            break;
        case DELETE:
            try {
                Task task = taskList.delete(Integer.parseInt(tokens[1]) - 1);
                message = Ui.getSuccessfullyDeletedMessage(taskList.getSize(), task);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new IncompleteInputException(command);
            }
            break;
        case FIND:
            String[] searchParameters = tokens[1].toLowerCase().split(" ");
            message = Ui.getFilteredTasksMessage(taskList.getFilteredTaskList(searchParameters));
            break;
        case LIST:
            message = Ui.getAllTasksMessage(taskList.getTasks());
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            try {
                Task task = taskList.addTask(command, tokens[1].trim());
                message = Ui.getSuccessfullyAddedTaskMessage(taskList.getSize(), task);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IncompleteInputException(command);
            }
            break;
        default:
            assert false : "command not recognised";
        }

        saveData();
        return message;
    }
}
