package duke;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

/**
 * Represents a Chatbot which can interact with the user
 * and perform note-taking functions.
 */
public class Duke {

    private static final String MESSAGE_COMMAND_GREET = "HELLO. I'M A BOT CALLED DUCHESS. Beep boop."
            + "\nWhat do you want?";
    private static final String MESSAGE_COMMAND_BYE = "BYE AND HAVE A GOOD DAY. Beep boop.";
    private static final String MESSAGE_COMMAND_UNDO = "Got it. I have undone your most recent change "
            + "to the tasklist.";
    private static final String MESSAGE_COMMAND_UNKNOWN = "Command not recognised.";
    private static final String MESSAGE_COMMAND_ERROR = "Error. Beep Boop.";
    private Storage storage;
    private TaskList tasks;
    private List<TaskList> history;

    /**
     * Creates a Duke Bot that interprets user input.
     *
     * @param filePath Location of the storage list
     */
    public Duke(String... filePath) {
        this.storage = new Storage(filePath);
        this.history = new ArrayList<>();
        try {
            this.tasks = this.storage.load();
        } catch (DukeException e) {
            this.showError(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Gets response in the form of a {@code Pair<DukeStatusCode, String>} given a user input.
     *
     * @param input Input from the user.
     * @return {@code Pair<Integer, String>} representing a statusCode, and the message to be printed.
     */
    public Pair<DukeStatusCode, String> getResponse(String input) {
        if (input.equals("")) {
            return this.noInput();
        }
        String[] commandArr = Parser.parseCommand(input);
        DukeCommand command = DukeCommand.fromString(commandArr[0]);
        String args = commandArr[1];
        try {
            switch (command) {
            case BYE:
                return this.bye();
            case UNKNOWN:
                return this.unknownCommand();
            case LIST:
                return this.listTask();
            case UNDO:
                return this.undoTask();
            case FIND:
                return this.findTask(args);
            case DELETE:
                return this.deleteTask(args);
            case DONE:
                return this.doTask(args);
            case TODO:
                return this.addTodoTask(args);
            case EVENT:
                return this.addEventTask(args);
            case DEADLINE:
                return this.addDeadlineTask(args);
            default:
                throw new DukeException(MESSAGE_COMMAND_ERROR);
            }
        } catch (DukeException e) {
            return this.error(e);
        } finally {
            try {
                this.saveTasks();
            } catch (DukeException e) {
                return this.error(e);
            }
        }
    }

    /**
     * Wrapper method if user presses send or enter without any message.
     *
     * @return {@code Pair<Integer, String>} representing a statusCode, and the message to be printed.
     *         The {@code DukeStatusCode} will be {@code DukeStatusCode.NO_ACTION}.
     */
    public Pair<DukeStatusCode, String> noInput() {
        return new Pair<DukeStatusCode, String> (DukeStatusCode.NO_ACTION, "");
    }

    /**
     * Wrapper method if a bye commands is received.
     *
     * @return {@code Pair<Integer, String>} representing a statusCode, and the message to be printed.
     *         The {@code DukeStatusCode} will be {@code DukeStatusCode.EXIT}.
     */
    public Pair<DukeStatusCode, String> bye() {
        return new Pair<DukeStatusCode, String> (DukeStatusCode.EXIT, MESSAGE_COMMAND_BYE);
    }

    /**
     * Wrapper method if unknown commands are received.
     *
     * @return {@code Pair<Integer, String>} representing a statusCode, and the message to be printed.
     *         The {@code DukeStatusCode} will be {@code DukeStatusCode.ERROR}.
     */
    public Pair<DukeStatusCode, String> unknownCommand() {
        return new Pair<DukeStatusCode, String> (DukeStatusCode.ERROR, MESSAGE_COMMAND_UNKNOWN);
    }

    /**
     * Wrapper method to list Tasks.
     *
     * @return {@code Pair<DukeStatusCode, String>} with statuscode representing the result of
     *         the method call, and a string representing the message to be displayed.
     */
    public Pair<DukeStatusCode, String> listTask() {
        return new Pair<DukeStatusCode, String> (DukeStatusCode.OK, this.tasks.listTasks());
    }

    /**
     * Undo most recent user command.
     *
     * @return {@code Pair<DukeStatusCode, String>} with statuscode representing the result of
     *         the method call, and a string representing the message to be displayed.
     * @throws DukeException if there is nothing to undo.
     */
    public Pair<DukeStatusCode, String> undoTask() throws DukeException {
        if (this.history.size() == 0) {
            throw new DukeException("There is nothing to undo.");
        }
        this.tasks = this.history.remove(this.history.size() - 1);
        return new Pair<DukeStatusCode, String> (DukeStatusCode.OK, MESSAGE_COMMAND_UNDO);
    }

    /**
     * Parses the input from the user and returns the result of a finding of a task.
     *
     * @param args The rest of the input from the user, excluding the initial command, representing
     *             the extra parameters required for the command.
     * @return {@code Pair<Integer, String>} representing a statusCode, and the message to be printed.
     * @throws DukeException if the required parameters for the specific command are missing.
     */
    public Pair<DukeStatusCode, String> findTask(String args) throws DukeException {
        String[] params = Parser.parseParams(DukeCommand.FIND, args);
        return new Pair<DukeStatusCode, String> (DukeStatusCode.OK, this.tasks.findTask(params[0]));
    }

    /**
     * Parses the input from the user and returns the result of a deletion of a task.
     * Also adds the pre-modification state of the TaskList into history so that the command can be undone.
     *
     * @param args The rest of the input from the user, excluding the initial command, representing
     *             the extra parameters required for the command.
     * @return {@code Pair<Integer, String>} representing a statusCode, and the message to be printed.
     * @throws DukeException if the required parameters for the specific command are missing,
     *                       or if the index is more than the amount of tasks in the taskList.
     */
    public Pair<DukeStatusCode, String> deleteTask(String args) throws DukeException {
        TaskList oldTaskList = new TaskList(this.tasks);
        String[] params = Parser.parseParams(DukeCommand.DELETE, args);
        int index = Parser.parseInt(params[0]);
        Pair<DukeStatusCode, String> response = new Pair<DukeStatusCode, String> (
                DukeStatusCode.OK, this.tasks.deleteTask(index));
        this.history.add(oldTaskList);
        return response;
    }

    /**
     * Parses the input from the user and returns the result of marking a task as done.
     * Also adds the pre-modification state of the TaskList into history so that the command can be undone.
     *
     * @param args The rest of the input from the user, excluding the initial command, representing
     *             the extra parameters required for the command.
     * @return {@code Pair<Integer, String>} representing a statusCode, and the message to be printed.
     * @throws DukeException if the required parameters for the specific command are missing,
     *                       or if the input cannot be parsed to an integer.
     */
    public Pair<DukeStatusCode, String> doTask(String args) throws DukeException {
        TaskList oldTaskList = new TaskList(this.tasks);
        String[] params = Parser.parseParams(DukeCommand.DONE, args);
        int index = Parser.parseInt(params[0]);
        Pair<DukeStatusCode, String> response = new Pair<DukeStatusCode, String> (
                DukeStatusCode.OK, this.tasks.doTask(index));
        this.history.add(oldTaskList);
        return response;
    }

    /**
     * Parses the input from the user and returns the result of adding a
     * Todo Task into {@code this.tasks}.
     * Also adds the pre-modification state of the TaskList into history so that the command can be undone.
     *
     * @param args The rest of the input from the user, excluding the initial command, representing
     *             the extra parameters required for the command.
     * @return {@code Pair<Integer, String>} representing a statusCode, and the message to be printed.
     * @throws DukeException if the required parameters for the specific command are missing,
     */
    public Pair<DukeStatusCode, String> addTodoTask(String args) throws DukeException {
        TaskList oldTaskList = new TaskList(this.tasks);
        String[] params = Parser.parseParams(DukeCommand.TODO, args);
        Todo todoTask = new Todo(params[0], TaskType.TODO);
        Pair<DukeStatusCode, String> response = new Pair<DukeStatusCode, String> (
                DukeStatusCode.OK, this.tasks.addTask(todoTask));
        this.history.add(oldTaskList);
        return response;
    }

    /**
     * Parses the input from the user and returns the result of adding an
     * Event Task into {@code this.tasks}.
     * Also adds the pre-modification state of the TaskList into history so that the command can be undone.
     *
     * @param args The rest of the input from the user, excluding the initial command, representing
     *             the extra parameters required for the command.
     * @return {@code Pair<Integer, String>} representing a statusCode, and the message to be printed.
     * @throws DukeException if the required parameters for the specific command are missing,
     *                       or if the string representation of the date input
     *                       by the user is not in the correct format.
     */
    public Pair<DukeStatusCode, String> addEventTask(String args) throws DukeException {
        TaskList oldTaskList = new TaskList(this.tasks);
        String[] params = Parser.parseParams(DukeCommand.EVENT, args);
        Event eventTask = new Event(params[0], TaskType.EVENT, params[1]);
        Pair<DukeStatusCode, String> response = new Pair<DukeStatusCode, String> (
                DukeStatusCode.OK, this.tasks.addTask(eventTask));
        this.history.add(oldTaskList);
        return response;
    }

    /**
     * Parses the input from the user and returns the result of adding a
     * Deadline Task into {@code this.tasks}.
     * Also adds the pre-modification state of the TaskList into history so that the command can be undone.
     *
     * @param args The rest of the input from the user, excluding the initial command, representing
     *             the extra parameters required for the command.
     * @return {@code Pair<Integer, String>} representing a statusCode, and the message to be printed.
     * @throws DukeException if the required parameters for the specific command are missing,
     *                       or if the string representation of the date input
     *                       by the user is not in the correct format.
     */
    public Pair<DukeStatusCode, String> addDeadlineTask(String args) throws DukeException {
        TaskList oldTaskList = new TaskList(this.tasks);
        String[] params = Parser.parseParams(DukeCommand.DEADLINE, args);
        Deadline deadlineTask = new Deadline(params[0], TaskType.DEADLINE, params[1]);
        Pair<DukeStatusCode, String> response = new Pair<DukeStatusCode, String> (
                DukeStatusCode.OK, this.tasks.addTask(deadlineTask));
        this.history.add(oldTaskList);
        return response;
    }

    /**
     * Returns an error response.
     *
     * @param e The exception
     * @return {@code Pair<Integer, String>} representing a statusCode, and the message to be printed.
     *         The {@code DukeStatusCode} will be {@code DukeStatusCode.ERROR}.
     *         The String will be a short description of the exception.
     */
    public Pair<DukeStatusCode, String> error(Exception e) {
        return new Pair<DukeStatusCode, String> (DukeStatusCode.ERROR, this.showError(e));
    }

    /**
     * Returns a short description of the exception.
     *
     * @param e The exception.
     * @return a short description of the excception.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Wrapper method to save tasks to the disk.
     *
     * @throws DukeException if the tasks cannot be saved.
     */
    public void saveTasks() throws DukeException {
        try {
            this.storage.save(this.tasks);
        } catch (DukeException e) {
            this.storage.load();
        }
        this.storage.save(this.tasks);
    }

    /**
     * Returns a greeting message string.
     */
    public String greet() {
        return MESSAGE_COMMAND_GREET;
    }
}

/**
 * Represents the different commands that the user can input.
 */
enum DukeCommand {
    UNKNOWN,
    BYE,
    LIST,
    UNDO,
    FIND,
    DELETE,
    DONE,
    TODO,
    EVENT,
    DEADLINE;

    /**
     * Generates a DukeCommand based on the lowecase version of the text.
     * If the command is unknown, returns DukeCommand.UNKNOWN
     *
     * @param command String that represents the command in lowercase.
     * @return DukeCommand. If the command is unknown, DukeCommand.UNKNOWN.
     */
    public static DukeCommand fromString(String command) {
        for (int i = 1; i < DukeCommand.values().length; i++) {
            if (DukeCommand.values()[i].toString().equals(command.toUpperCase())) {
                return DukeCommand.values()[i];
            }
        }
        return DukeCommand.values()[0];
    }
}

enum DukeStatusCode {
    OK,
    NO_ACTION,
    ERROR,
    EXIT,
}
