package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;

/**
 * Represents a list command to return the contents of the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    /**
     * Prints all tasks in taskList.
     *
     * @return String representation of all tasks in taskList.
     */
    @Override
    public String execute() {
        if (this.taskList.isEmpty()) {
            return this.taskList.getListInString();
        }

        String msg = "Here are the tasks you have:\n" + this.taskList.getListInString();
        return msg;
    }
}
