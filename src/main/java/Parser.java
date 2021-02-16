/**
 * Parser takes in a user command, parses it and returns the corresponding command.
 */
public class Parser {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a parser object.
     *
     * @param taskList list of tasks.
     * @param ui standard ui object.
     * @param storage standard storage object.
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Takes in command in the form of a string and returns a command object.
     *
     * @param command user command input.
     * @return respective command object based on user input.
     * @throws DukeWrongInputException if system does not understand user input.
     */
    public static Command parse(String command) throws DukeWrongInputException {
        String[] commandArr = command.trim().toLowerCase().split(" ");
        command = command.trim();
        assert !commandArr[0].contains(" ");
        switch(commandArr[0]) {
        case "list":
            return new ListCommand(command);
        case "done":
            return new DoneCommand(command);
        case "todo":
            return new TodoCommand(command);
        case "event":
            return new EventCommand(command);
        case "deadline":
            return new DeadlineCommand(command);
        case "delete":
            return new DeleteCommand(command);
        case "find":
            return new FindCommand(command);
        case "bye":
            return new ExitCommand(command);
        case "sort":
            return new SortCommand();
        default:
            throw new DukeWrongInputException("OOPS! I'm sorry, I don't understand.");
        }
    }
}
