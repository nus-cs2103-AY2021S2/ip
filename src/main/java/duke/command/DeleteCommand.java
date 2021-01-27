package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.exception.DukeException;
import duke.exception.DukeCommandException;

/** An executable command to delete a specified task */
public class DeleteCommand extends Command {
    /** Index of the task that is going to be deleted */
    private int index;

    /**
     * Constructor of a DeleteCommand
     *
     * @param index Index of a task that this command is going to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command to delete a task from the list
     *
     * @throws DukeCommandException if there is no tasks to delete, index is out of range, an issue with deleting task
     * from the task list or an issue with saving the deletion to the hard disk
     */
    @Override
    public void execute() throws DukeCommandException {
        if (taskManager.getTasksSize() == 0){
            throw new DukeCommandException("delete", String.valueOf(index), "There are no task to be deleted.");
        } else if (index < 0 || index >= taskManager.getTasksSize()) {
            throw new DukeCommandException("delete", String.valueOf(index), "Please enter a valid task index " +
                    "ranging from 1 to " + taskManager.getTasksSize() + " (inclusive).");
        } else {
            try {
                Task task = taskManager.deleteTask(index);
                ui.printDeleteMsg(task, taskManager.getTasksSize());
                Storage.saveTasks(taskManager.getTasks());
            } catch(DukeException e) {
                throw new DukeCommandException("delete", String.valueOf(index), e.getMessage());
            }
        }
    }
}
