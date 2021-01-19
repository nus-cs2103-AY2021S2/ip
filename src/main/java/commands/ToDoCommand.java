package commands;

import duke.Ui;
import duke.TaskManager;

public class ToDoCommand extends Command {
    private String name;

    public ToDoCommand(String name) {
        this.name = name;
    }

    public void execute(Ui ui, TaskManager tm) {
        tm.addToDoTask(name);
    }
}
