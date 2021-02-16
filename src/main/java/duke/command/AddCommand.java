package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command telling duke to add a task to its task list
 */
public class AddCommand implements Command {
    private Task taskToAdd;

    /**
     * Constructor
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponString(TaskList tasks, Storage storage) {
        tasks.add(this.taskToAdd);
        try {
            storage.store(tasks);
        } catch (IOException e) {
            throw new DukeException("Cannot save tasks. Save file not found");
        }
        String numOfTasks = tasks.size() + (tasks.size() > 1 ? " tasks" : " task");
        String addResponse = "Got it. I've added this task:\n  " + this.taskToAdd
                + "\nNow you have " + numOfTasks + " in the list.";
        return addResponse;
    }
}
