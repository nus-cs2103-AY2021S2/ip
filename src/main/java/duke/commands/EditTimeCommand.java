package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class EditTimeCommand extends Command {
    private int taskNum;
    private String newTime;

    /**
     *Delete command constructor
     * @param taskNum Index of task to be deleted from list
     */
    public EditTimeCommand(int taskNum, String newTime) {
        super(CommandType.EDIT_TIME);
        this.taskNum = taskNum;
        this.newTime = newTime;
    }

    /**
     * Changes task time from list and prints ui message
     * @param list List of tasks
     */
    @Override
    public String execute(TaskList list) {
        Task curr = list.getTask(taskNum - 1);
        assert curr instanceof Deadline || curr instanceof Event;
        curr.editTime(this.newTime);
        return ui.printTimeEdit(curr, this.taskNum);
    }
}
