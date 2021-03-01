import java.util.ArrayList;

/**
 * The Parser class takes in the user input, parses it,
 * then display the corresponding message from Mister Duke
 */
public class Parser {
    private final Ui ui;
    private Storage storage;
    private ArrayList<Task> taskList;

    public Parser(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * The parse function takes in the user's input (command) and
     * returns a class that corresponds to the command
     * @param command user input
     * @return class that corresponds to the command
     * @throws DukeException when the user input is wrong/incomplete
     */
    public static Command parse(String command) throws DukeException {
        String input = command.trim();
        String[] strArray = input.split(" ", 2);
        String cmd = strArray[0].trim();
        assert !cmd.contains(" ");

        switch (cmd) {
            case "todo":
                return new ToDoCommand(command);
            case "deadline":
                return new DeadlineCommand(command);
            case "event":
                return new EventCommand(command);
            case "list":
                return new ListCommand(command);
            case "done":
                return new DoneCommand(command);
            case "delete":
                return new DeleteCommand(command);
            case "bye":
                return new ExitCommand(command);
            case "find":
                return new FindCommand(command);
            case "note":
                return new NoteCommand(command);
            default:
                throw new DukeException("Oops! I'm sorry but I don't know what you mean by that :(");
        }
    }
}
