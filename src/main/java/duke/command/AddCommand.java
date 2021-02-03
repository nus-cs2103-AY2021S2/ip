package duke.command;

import duke.common.DukeString;
import duke.task.Task;
import duke.task.TaskList;

/**
 * An abstract class that, when executed, will add the given Task to the given TaskList.
 */
public abstract class AddCommand implements Command {
    private final Task task;

    /**
     * Constructor accessible by subclasses.
     *
     * @param task the task to be added to the task list.
     */
    protected AddCommand(final Task task) {
        this.task = task;
    }

    /**
     * Adds the associated task to the task list.
     *
     * @param taskList the task list to be modified by the command.
     * @return a formatted message to be output to the user.
     */
    @Override
    public String execute(final TaskList taskList) {
        taskList.addTask(task);
        return String.format(DukeString.MESSAGE_ADDED, task.toString(), taskList.getSize());
    }
}
