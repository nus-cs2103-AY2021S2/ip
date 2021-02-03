package main.java.classes;

import main.java.command.*;

public class Parser {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private static String[] command = {"list", "bye", "todo", "deadline", "event", "done", "delete", "find"};

    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public static Command parse(String cmd) throws DuckieException {
        String[] commandArr = cmd.trim().split(" ");
        cmd = cmd.trim();
        switch(commandArr[0]) {
            case "list":
                return new ListCmd(cmd);
            case "todo":
                return new ToDoCmd(cmd);
            case "event":
                return new EventCmd(cmd);
            case "deadline":
                return new DeadlineCmd(cmd);
            case "done":
                return new DoneCmd(cmd);
            case "delete":
                return new DeleteCmd(cmd);
            case "find":
                return new FindCmd(cmd);
            case "bye":
                return new ByeCmd(cmd);
            default:
                throw new DuckieException("please start with 'list', 'todo', 'event', deadline', 'delete', 'done' or 'bye'");
        }
    }
}
