package main.java.command;

import main.java.TaskManager;
import main.java.Ui;

/**
 * Command to list all tasks
 */
public class ListCommand extends Command {

    /**
     * execute list tasks command
     * call TaskManager get a list of all tasks
     * and Ui to display all these tasks
     * @param tm Associated TaskManager
     * @param ui Associated Ui
     * @return command execution result string
     */
    @Override
    public String execute(TaskManager tm, Ui ui) {
        if (tm.isEmpty()) {
            return ui.displayEmptyList();
        } else {
            return ui.displayAllTasks(tm.getList());
        }
    }
}
