package main.java.command;

import main.java.TaskManager;
import main.java.Ui;

public class ListCommand  extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskManager tm, Ui ui) {
        if (tm.isEmpty()) {
            ui.displayEmptyList();
        } else {
            ui.displayAllTasks(tm.getList());
        }
    }
}
