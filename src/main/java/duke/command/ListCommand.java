package duke.command;

import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command for showing all the tasks in the list of tasks.
 */
public class ListCommand extends Command {
    public static final boolean IS_EXIT = false;

    /**
     * Constructor method
     */
    public ListCommand() {
        super(IS_EXIT);
    }

    /**
     * Executes method for list command.
     * @param tasks The tasks in the TaskList.
     * @param ui Standard UI object
     * @param storage Standard storage object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder("    Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            int j = i + 1;
            String toAppend = "    " + (j) + "." + task.toString();
            sb.append(toAppend + "\n");
        }
        return sb.toString();
    }
}
