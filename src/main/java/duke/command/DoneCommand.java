package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.Task;
import duke.task.TaskList;


/**
 * DoneCommand handles the marking of tasks as done status in the list only
 */
public class DoneCommand extends Command {

    /**
     * DoneCommand Constructor
     *
     * @param commandType Task name
     * @param index Integer index on the display list in command line
     */
    public DoneCommand(String commandType, int index) {
        super.commandType = commandType;
        super.commandDetails = String.valueOf(index);
        super.dateTime = "";
        super.outputMessage = "";
        super.index = index;
    }

    private void markDoneTask(TaskList taskList) {
        Task doneTask = taskList.get(this.index - 1);
        doneTask.markAsDone();
        this.outputMessage = " Nice! I've marked this task as done:\n" + "\t  " + doneTask.toString();
    }

    /**
     * Mark selected task as done status from the TaskList,
     * Save the updated TaskList into file and output message to command line
     *
     * @param tasks TaskList
     * @param storage Instance of Storage
     * @throws DukeException If the integer input is out of list range (negative, 0 or greater than TaskList size)
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException(ExceptionType.INVALID_INTEGER, "");
        }

        markDoneTask(tasks);
        storage.saveData(tasks);
    }

    /**
     * Determines if whether the Duke Bot should continue processing the user input
     *
     * @return True
     */
    @Override
    public boolean continueInput() {
        return true;
    }
}
