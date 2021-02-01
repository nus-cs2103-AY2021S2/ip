package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.Task;
import duke.task.TaskList;

/**
 * DeleteTask handles the deletion of event, deadline and todo tasks to the list only
 */
public class DeleteTask extends Command {

    /**
     * DeleteTask Constructor
     *
     * @param commandType Task name
     * @param index Integer index on the display list in command line
     */
    public DeleteTask(String commandType, int index) {
        super.commandType = commandType;
        super.commandDetails = String.valueOf(index);
        super.dateTime = "";
        super.outputMessage = "";
        super.index = index;
    }

    private void handleDeleteTask(TaskList taskList) {
        Task deleteTask = taskList.get(index - 1);
        taskList.remove(deleteTask);
        this.outputMessage = "Noted. I've removed this task: \n" + "\t  " + deleteTask.toString()
                + "\n\t Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Delete selected task from the TaskList, save the updated TaskList into file and output message to command line
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

        handleDeleteTask(tasks);
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
