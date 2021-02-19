package duke.command;

import java.io.IOException;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command telling duke to delete a task
 */
public class DeleteCommand implements Command {
    private int taskNum;

    /**
     * Constructor
     * @param taskNum The task number of the task to be deleted
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (this.taskNum > tasks.size()) {
            throw new DukeException("No such task exists!");
        }
        Task deletedTask = tasks.get(taskNum);
        tasks.delete(taskNum);

        try {
            storage.store(tasks);
        } catch (IOException e) {
            throw new DukeException("Cannot save delete. Save file not found");
        }

        String numOfTasks = tasks.size() + (tasks.size() > 1 ? " tasks" : " task");
        String deleteResponse = "Noted. I've removed this task:\n  " + deletedTask + "\nNow you have "
                + numOfTasks + " in the list.";
        return deleteResponse;
    }
}
