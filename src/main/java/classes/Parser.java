package main.java.classes;

import main.java.command.ByeCmd;
import main.java.command.Command;
import main.java.command.DeadlineCmd;
import main.java.command.DeleteCmd;
import main.java.command.DoneCmd;
import main.java.command.EventCmd;
import main.java.command.ListCmd;
import main.java.command.ToDoCmd;

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

    public static Command parse(String cmd) throws DuckieException {
        String[] commandArr = cmd.trim().split(" ");
        cmd = cmd.trim();
        switch(commandArr[0]) {
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
            case "bye":
                return new ByeCmd(cmd);
            default:
                throw new DuckieException("please start with 'list', 'todo', 'event', deadline', 'delete', 'done' or 'bye'");
        }
    }
}
