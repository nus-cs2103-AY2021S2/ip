package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Encapsulates the information of a parsed user command to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskId;

    /**
     * @param fullCommand The full user command.
     * @param taskId The task id of the task which will be deleted.
     */
    public DeleteCommand(String fullCommand, int taskId) {
        super(fullCommand);
        this.taskId = taskId;
    }

    /**
     * Executes the user command to delete task.
     * @param tasks A TaskList object which encapsulates the data and operations on a task list.
     * @param ui A duke.Ui object which deals with interactions with the user.
     * @param storage A duke.Storage object which deals with loading tasks from the file and saving tasks in the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskId);
        ui.printMsg("Noted. I've removed this task: ");
        ui.printMsg("  " + task);
        ui.printMsg("Now you have " + tasks.getNumOfTasks() + " tasks in the list.");
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
