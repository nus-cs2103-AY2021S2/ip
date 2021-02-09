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
            return new Pair<DukeStatusCode, String> (DukeStatusCode.NO_ACTION, "");
        }
        String[] commandArr = Parser.parseCommand(input);
        String[] params;
        int index;
        Pair<DukeStatusCode, String> response;
        TaskList oldTaskList = new TaskList(this.tasks);
        DukeCommand command = DukeCommand.fromString(commandArr[0]);
        try {
            switch (command) {
            case BYE:
                return new Pair<DukeStatusCode, String> (DukeStatusCode.EXIT, MESSAGE_COMMAND_BYE);
            case UNKNOWN:
                return new Pair<DukeStatusCode, String> (DukeStatusCode.ERROR, MESSAGE_COMMAND_UNKNOWN);
            case LIST:
                return new Pair<DukeStatusCode, String> (DukeStatusCode.OK, this.tasks.listTasks());
            case UNDO:
                return new Pair<DukeStatusCode, String> (DukeStatusCode.OK, this.undoTask());
            case FIND:
                params = Parser.parseParams(command, commandArr[1]);
                return new Pair<DukeStatusCode, String> (DukeStatusCode.OK, this.tasks.findTask(params[0]));
            case DELETE:
                params = Parser.parseParams(command, commandArr[1]);
                index = Parser.parseInt(params[0]);
                response = new Pair<DukeStatusCode, String> (DukeStatusCode.OK, this.tasks.deleteTask(index));
                this.history.add(oldTaskList);
                return response;
            case DONE:
                params = Parser.parseParams(command, commandArr[1]);
                index = Parser.parseInt(params[0]);
                response = new Pair<DukeStatusCode, String> (DukeStatusCode.OK, this.tasks.doTask(index));
                this.history.add(oldTaskList);
                return response;
            case TODO:
                params = Parser.parseParams(command, commandArr[1]);
                response = new Pair<DukeStatusCode, String> (DukeStatusCode.OK, this.tasks.addTask(
                    new Todo(params[0], TaskType.TODO)
                ));
                this.history.add(oldTaskList);
                return response;
            case EVENT:
                params = Parser.parseParams(command, commandArr[1]);
                response = new Pair<DukeStatusCode, String> (DukeStatusCode.OK, this.tasks.addTask(
                    new Event(params[0], TaskType.EVENT, params[1])
                ));
                this.history.add(oldTaskList);
                return response;
            case DEADLINE:
                params = Parser.parseParams(command, commandArr[1]);
                response = new Pair<DukeStatusCode, String> (DukeStatusCode.OK, this.tasks.addTask(
                    new Deadline(params[0], TaskType.DEADLINE, params[1])
                ));
                this.history.add(oldTaskList);
                return response;
            default:
                throw new DukeException(MESSAGE_COMMAND_ERROR);
            }
        } catch (DukeException e) {
            return new Pair<DukeStatusCode, String> (DukeStatusCode.ERROR, this.showError(e));
        } finally {
            try {
                this.storage.save(this.tasks);
            } catch (DukeException e) {
                return new Pair<DukeStatusCode, String> (DukeStatusCode.ERROR, this.showError(e));
            }
        }
    }

    /**
     * Undo most recent user command.
     *
     * @return String representing Duke's Reply to user.
     */
    public String undoTask() throws DukeException {
        if (this.history.size() == 0) {
            throw new DukeException("There is nothing to undo.");
        }
        this.tasks = this.history.remove(this.history.size() - 1);
        return MESSAGE_COMMAND_UNDO;
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
     * Returns a greeting message string.
     */
    public String greet() {
        return MESSAGE_COMMAND_GREET;
    }
}

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
