package main.java.command;

import main.java.TaskManager;
import main.java.Ui;
import main.java.entity.Task;

/**
 * Command to delete a task
 */
public class DeleteCommand extends Command {
    private int deleteIndex;

    /**
     * Creates a Command to delete a task
     * @param deleteIndex task-to-delete index
     */
    public DeleteCommand(int deleteIndex) {
        super();
        this.deleteIndex = deleteIndex;
    }

    /**
     * execute delete task command
     * call TaskManager to delete the particular task
     * and Ui to display delete message
     * @param tm Associated TaskManager
     * @param ui Associated Ui
     * @return command execution result string
     */
    @Override
    public String execute(TaskManager tm, Ui ui) {
        try {
            if (tm.indexWithinRange(deleteIndex)) {
                Task task = tm.deleteTask(deleteIndex);
                return ui.displayAfterDelete(deleteIndex, task);
            } else {
                return ui.displayOutOfRange(deleteIndex);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
