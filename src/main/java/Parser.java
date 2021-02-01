package main.java;

public class Parser {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private static String[] command = {"list", "bye", "todo", "deadline", "event", "done", "delete"};

    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public static Command parse(String command) throws DuckieException {
        String[] commandArr = command.trim().split(" ");
        command = command.trim();
        switch(commandArr[0]) {
            case "list":
                return new ListCmd(command);
            case "done":
                return new DoneCmd(command);
            case "todo":
                return new ToDoCmd(command);
            case "event":
                return new EventCmd(command);
            case "deadline":
                return new DeadlineCmd(command);
            case "delete":
                return new DeleteCmd(command);
            case "bye":
                return new ByeCmd(command);
            default:
                throw new DuckieException("please start with 'list', 'todo', 'event', deadline', 'delete', 'done' or 'bye'");
        }
    }
}
