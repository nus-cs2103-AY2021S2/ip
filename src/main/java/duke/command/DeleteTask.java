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
        super.commandDetail = String.valueOf(index);
        super.dateTime = "";
        super.outputMessage = "";
        super.index = index;
    }

    private void handleDeleteTask(TaskList taskList) {
        Task deleteTask = taskList.getTask(index - 1);
        taskList.removeTask(deleteTask);
        String taskDetail = deleteTask.toString();
        int numTasks = taskList.getSize();

        this.outputMessage = "Noted. I've removed this task: \n" + "\t  " + taskDetail
                + "\n\t Now you have " + numTasks + " tasks in the list.";
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
        boolean isValidIndex = this.index <= 0 || this.index > tasks.getSize();

        if (isValidIndex) {
            throw new DukeException(ExceptionType.INVALID_INTEGER, "");
        }
        handleDeleteTask(tasks);
        storage.saveData(tasks);
    }
}
