package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command for deleting tasks from list
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     *Delete command constructor
     * @param taskNum Index of task to be deleted from list
     */
    public DeleteCommand(int taskNum) {
        super(CommandType.DELETE);
        this.taskNum = taskNum;
    }

    /**
     * Deletes task from list and prints ui message
     * @param list List of tasks
     */
    @Override
    public void execute(TaskList list) {
        Task curr = list.delete(taskNum - 1);
        ui.printDeleteMsg(curr, list.size());
    }
}
