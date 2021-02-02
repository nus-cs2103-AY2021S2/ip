package duke;

import javafx.util.Pair;

public class Duke {

    private static final String MESSAGE_COMMAND_GREET = "HELLO. I'M A BOT CALLED DUCHESS. Beep boop."
            + "\nWhat do you want?";
    private static final String MESSAGE_COMMAND_BYE = "BYE AND HAVE A GOOD DAY. Beep boop.";
    private static final String MESSAGE_COMMAND_UNKNOWN = "Command not recognised.";
    private static final String MESSAGE_COMMAND_ERROR = "Error. Beep Boop.";
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Duke Bot that interprets user input.
     *
     * @param filePath Location of the storage list
     */
    public Duke(String[] filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = this.storage.load();
        } catch (DukeException e) {
            this.showError(e);
            this.tasks = new TaskList();
        }
    }

    public Pair<Integer, String> getResponse(String input) {
        String[] commandArr = Parser.parseCommand(input);
        String[] params;
        int index;
        DukeCommand command = DukeCommand.fromString(commandArr[0]);
        try {
            switch (command) {
            case BYE:
                return new Pair<Integer, String> (1, MESSAGE_COMMAND_BYE);
            case UNKNOWN:
                return new Pair<Integer, String> (0, MESSAGE_COMMAND_UNKNOWN);
            case LIST:
                return new Pair<Integer, String> (0, this.tasks.listTasks());
            case DELETE:
                params = Parser.parseParams(command, commandArr[1]);
                index = Parser.parseInt(params[0]);
                return new Pair<Integer, String> (0, this.tasks.deleteTask(index));
            case DONE:
                params = Parser.parseParams(command, commandArr[1]);
                index = Parser.parseInt(params[0]);
                return new Pair<Integer, String> (0, this.tasks.doTask(index));
            case FIND:
                params = Parser.parseParams(command, commandArr[1]);
                return new Pair<Integer, String> (0, this.tasks.findTask(params[0]));
            case TODO:
                params = Parser.parseParams(command, commandArr[1]);
                return new Pair<Integer, String> (0, this.tasks.addTask(
                    new Todo(params[0], TaskType.TODO)
                ));
            case EVENT:
                params = Parser.parseParams(command, commandArr[1]);
                return new Pair<Integer, String> (0, this.tasks.addTask(
                    new Event(params[0], TaskType.EVENT, params[1])
                ));
            case DEADLINE:
                params = Parser.parseParams(command, commandArr[1]);
                return new Pair<Integer, String> (0, this.tasks.addTask(
                    new Deadline(params[0], TaskType.DEADLINE, params[1])
                ));
            default:
                throw new DukeException(MESSAGE_COMMAND_ERROR);
            }
        } catch (DukeException e) {
            return new Pair<Integer, String> (0, this.showError(e));
        } finally {
            try {
                this.storage.save(this.tasks);
            } catch (DukeException e) {
                return new Pair<Integer, String> (0, this.showError(e));
            }
        }
    }

    /**
     * Returns a short description of the exception.
     *
     * @param e The exception.
     * @return a short description of the excception.
     */
    public String showError(Exception e) {
        // System.out.println(e);
        return e.toString();
    }

    /**
     * Prints a greeting message.
     */
    public String greet() {
        return MESSAGE_COMMAND_GREET;
    }
}

enum DukeCommand {
    UNKNOWN,
    BYE,
    LIST,
    DELETE,
    DONE,
    FIND,
    TODO,
    EVENT,
    DEADLINE;

    public static DukeCommand fromString(String command) {
        for (int i = 1; i < DukeCommand.values().length; i++) {
            if (DukeCommand.values()[i].toString().equals(command.toUpperCase())) {
                return DukeCommand.values()[i];
            }
        }
        return DukeCommand.values()[0];
    }
}
