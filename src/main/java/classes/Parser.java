package classes;

import command.*;


/**
 * Parser class to deal with making sense of the user command.
 */
public class Parser {
    private static String[] command = {"list", "bye", "todo", "deadline", "event", "done", "delete", "find", "doWithin"};
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor method.
     *
     * @param taskList TaskList object storing list of tasks
     * @param ui       a Ui object
     * @param storage  a Storage object
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parse method makes sense of the user input.
     * Returns a command class from user's String input.
     * @param cmd user input
     * @return command class corresponding to user input cmd
     * @throws DuckieException if commands other than the enumerated keywords are entered
     */
    public static Command parse(String cmd) throws DuckieException {
        assert(cmd != null);
        String[] commandArr = cmd.trim().split(" ");
        cmd = cmd.trim();
        assert commandArr[0].length() == 1;
        switch (commandArr[0]) {
        case "list":
            return new ListCmd(cmd);
        // Fallthrough
        case "todo":
            return new ToDoCmd(cmd);
        // Fallthrough
        case "event":
            return new EventCmd(cmd);
        // Fallthrough
        case "deadline":
            return new DeadlineCmd(cmd);
        // Fallthrough
        case "done":
            return new DoneCmd(cmd);
        // Fallthrough
        case "delete":
            return new DeleteCmd(cmd);
        // Fallthrough
        case "find":
            return new FindCmd(cmd);
        // Fallthrough
        case "bye":
            return new ByeCmd(cmd);
        // Fallthrough
        case "doWithin":
            return new DoWithinCmd(cmd);
        // Fallthrough
        default:
            throw new DuckieException("command not recognized! "
                    + "please start with 'list', 'todo', 'event', 'doWithin', deadline', 'delete', 'done' or 'bye'");
        }
    }
}
