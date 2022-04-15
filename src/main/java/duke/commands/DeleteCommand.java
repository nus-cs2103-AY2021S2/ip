package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;

/**
 * Represents a delete command which deletes a task at the specified index in the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private int position;

    /**
     * Creates a DeleteCommand object to store the delete command input from the user.
     *
     * @param taskList the current list of Tasks.
     * @param storage the object in charge of writing to the local storage file.
     * @param position the position of the task to delete from the taskList.
     */
    public DeleteCommand(TaskList taskList, Storage storage, int position) {
        super(taskList, storage);
        this.position = position;
    }

    /**
     * Deletes Task at the previously specified position in the taskList.
     *
     * Thereafter, prints confirmation and remaining number of tasks.
     * @return message confirming that indicated task is deleted.
     */
    @Override
    public String execute() {
        String msg = "Noted. I've removed this task:\n" + this.taskList.getList().get(this.position);
        this.taskList.deleteTask(this.position);
        String numTasksLeft = "\nNow you have " + this.taskList.getList().size() + " tasks in the list.";
        return msg + numTasksLeft;
    }
}
