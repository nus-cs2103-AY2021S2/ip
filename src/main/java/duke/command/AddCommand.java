package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Encapsulates information about a parsed user command.
 */
public class AddCommand extends Command {
    private Task newTask;

    /**
     * @param fullCommand The full user command.
     * @param newTask The new task that will be added in the add command.
     */
    public AddCommand(String fullCommand, Task newTask) {
        super(fullCommand);
        this.newTask = newTask;
    }

    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.addTask(newTask);
        String resp = "Got it. I've added this task: \n" +  "  " + newTask +
                "Now you have " + tasks.getNumOfTasks() + " tasks in the list.";
        storage.save(tasks);
        return resp;
    }

    public boolean isExit() {
        return false;
    }
}