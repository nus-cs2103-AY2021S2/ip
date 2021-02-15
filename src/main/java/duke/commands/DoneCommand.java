package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;

/**
 * Represents a done command which marks a task at the specified index in the task list as done.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    private int position;

    /**
     * Creates a DoneCommand object to store the done command input from the user.
     *
     * @param taskList the current list of Tasks.
     * @param storage the object in charge of writing to the local storage file.
     * @param position the position of the task in the taskList to mark as done.
     */
    public DoneCommand(TaskList taskList, Storage storage, int position) {
        super(taskList, storage);
        this.position = position;
    }

    /**
     * Returns message indicating whether the Task's done status is successfully changed.
     *
     * @return message indicating the change in Task done status, if any.
     */
    @Override
    public String execute() {
        Task currentTask = this.taskList.getList().get(this.position);

        if (currentTask.isDone()) {
            String alreadyDoneMsg = "This task is already done!";
            return alreadyDoneMsg;
        }

        this.taskList.setTaskDone(this.position);
        String markedDoneMsg = "Nice! I've marked this task as done:\n" + this.taskList.getList().get(this.position);
        return markedDoneMsg;
    }
}
