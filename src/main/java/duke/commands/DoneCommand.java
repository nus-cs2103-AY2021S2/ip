package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command for marking a task as done
 */
public class DoneCommand extends Command {
    private int taskNum;

    /**
     * Done command constructor
     *
     * @param taskNum Index of task to be marked as done
     */
    public DoneCommand(int taskNum) {
        super(CommandType.DONE);
        this.taskNum = taskNum;
    }

    /**
     * Marks a task as done and prints ui message
     *
     * @param list List of tasks
     */
    @Override
    public String execute(TaskList list) {
        Task curr = list.markCompleted(taskNum - 1);
        return ui.printCompletedMsg(curr);
    }
}
