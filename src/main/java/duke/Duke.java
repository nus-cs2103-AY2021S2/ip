package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.IncompleteInputException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.utils.Command;
import duke.utils.HelpMessages;
import duke.utils.Messages;

/**
 * Main class of the application.
 */
public class Duke {
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Creates a new instance of duke.Duke.
     *
     * @param filePath The save file path.
     */
    public Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);
        taskList = new TaskList();
        loadData();
    }

    /**
     * Returns a response from duke based on the input.
     *
     * @param input User input.
     * @return Response from duke.
     */
    public String getResponse(String input) {
        try {
            String[] tokens = Parser.splitIntoSubstrings(input);
            Command command = Parser.parseCommand(tokens[0]);
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

    private String handleDone(Command command, String[] tokens) throws DukeException {
        try {
            Task task = taskList.markAsDone(Integer.parseInt(tokens[1]) - 1);
            return Messages.getSuccessfullyDoneMessage(task);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IncompleteInputException(command);
        }
    }

    private String handleDelete(Command command, String[] tokens) throws DukeException {
        try {
            Task task = taskList.delete(Integer.parseInt(tokens[1]) - 1);
            return Messages.getSuccessfullyDeletedMessage(taskList.getSize(), task);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IncompleteInputException(command);
        }
    }

    private String handleFind(String[] tokens) {
        String[] searchParameters = tokens[1].toLowerCase().split(" ");
        return Messages.getFilteredTasksMessage(taskList.getFilteredTaskList(searchParameters));
    }

    private String handleHelp(String[] tokens) {
        boolean isDescriptionEmpty = tokens.length < 2;
        return isDescriptionEmpty ? HelpMessages.getMessage("") : HelpMessages.getMessage(tokens[1]);
    }

    private String handleTask(Command command, String[] tokens) throws DukeException {
        try {
            Task task = taskList.addTask(command, tokens[1].trim());
            return Messages.getSuccessfullyAddedTaskMessage(taskList.getSize(), task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncompleteInputException(command);
        }
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
            message = Messages.getGoodbyeMessage();
            System.exit(0);
            break;
        case DONE:
            message = handleDone(command, tokens);
            break;
        case DELETE:
            message = handleDelete(command, tokens);
            break;
        case FIND:
            message = handleFind(tokens);
            break;
        case LIST:
            message = Messages.getAllTasksMessage(taskList.getTasks());
            break;
        case HELP:
            handleHelp(tokens);
            break;
        case TODO: // Fall through
        case DEADLINE: // Fall through
        case EVENT:
            handleTask(command, tokens);
            break;
        default:
            assert false : "command not recognised";
        }

        saveData();
        assert !message.equals("") : "message should not be empty string";
        return message;
    }
}
