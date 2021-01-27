package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.Task;

/**
 * Command for adding tasks into list
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Add command constructor
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        super(CommandType.ADD);
        this.task = task;
    }

    /**
     * Adds task into given list and prints ui message
     * @param list List of tasks
     */
    @Override
    public void execute(TaskList list) {
        list.addTask(task);
        ui.printTask(task, list.size());
    }
}
