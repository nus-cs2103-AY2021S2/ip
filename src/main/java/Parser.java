public class Parser {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public static Command parse(String command) throws DukeWrongInputException {
        // User input
        String[] commandArr = command.trim().split(" ");
        command = command.trim();
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
        case "bye":
            return new ExitCommand(command);
        default:
            throw new DukeWrongInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}