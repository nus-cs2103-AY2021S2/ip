package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command for editing name of specifice task
 */
public class EditNameCommand extends Command {
    private int taskNum;
    private String newName;

    /**
     *Editing name command constructor
     *@param taskNum Index of task to be deleted from list
     */
    public EditNameCommand(int taskNum, String newName) {
        super(CommandType.EDIT_NAME);
        this.taskNum = taskNum;
        this.newName = newName;
    }

    /**
     * Changes task name from list and prints ui message
     * @param list List of tasks
     */
    @Override
    public String execute(TaskList list) {
        try {
            Task curr = list.getTask(taskNum - 1);
            curr.editName(this.newName);
            return ui.printNameEdit(curr, this.taskNum);
        } catch (IndexOutOfBoundsException e) {
            return ui.printIndexOutOfBoundError();
        }
    }
}
