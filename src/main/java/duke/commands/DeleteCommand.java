package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private int position;

    /**
     * Creates a DeleteCommand object to store the delete command input from the user.
     * @param taskList the current list of Tasks.
     * @param ui the object in charge of printing user-friendly outputs.
     * @param storage the object in charge of writing to the local storage file.
     * @param position the position of the task to delete from the taskList.
     */
    public DeleteCommand(TaskList taskList, Ui ui, Storage storage, int position) {
        super(taskList, ui, storage);
        this.position = position;
    }

    /**
     * Deletes Task at the previously specified position in the taskList.
     * Thereafter, prints confirmation and remaining number of tasks.
     * @return message confirming that indicated task is deleted.
     */
    @Override
    public String execute() {
        String msg = "Noted. I've removed this task:\n" + this.taskList.getList().get(this.position);
        String numTasksLeft = "Now you have " + this.taskList.getList().size() + " tasks in the list.";
        this.taskList.deleteTask(this.position);
        return msg + numTasksLeft;
    }
}
