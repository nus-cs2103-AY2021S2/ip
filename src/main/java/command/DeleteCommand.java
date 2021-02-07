package main.java.command;

import java.util.ArrayList;
import java.util.List;

import main.java.TaskManager;
import main.java.Ui;
import main.java.entity.Task;

/**
 * Command to delete a task
 */
public class DeleteCommand extends Command {
    private List<Integer> deleteList;

    /**
     * Creates a Command to delete tasks
     * @param deleteList list of task-to-delete indices
     */
    public DeleteCommand(List<Integer> deleteList) {
        super();
        this.deleteList = deleteList;
    }

    /**
     * execute delete task command
     * call TaskManager to delete the tasks in list
     * and Ui to display delete message
     * @param tm Associated TaskManager
     * @param ui Associated Ui
     * @return command execution result string
     */
    @Override
    public String execute(TaskManager tm, Ui ui) {
        try {
            for (int deleteIndex : deleteList) {
                if (!tm.indexWithinRange(deleteIndex)) {
                    return ui.displayOutOfRange(deleteIndex);
                }
            }
            List<Task> taskList = new ArrayList<>();
            for (int deleteIndex : deleteList) {
                taskList.add(tm.getList().get(deleteIndex));
            }
            tm.deleteTask(taskList);
            return ui.displayAfterDelete(tm.size(), taskList);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
