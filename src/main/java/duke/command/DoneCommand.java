package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.DukeCommandException;

/** An executable command to set a task as completed */
public class DoneCommand extends Command {
    /** Index of the task that is going to be completed */
    private int index;

    /**
     * Constructor of a DoneCommand
     * @param index Index of a task that will be completed
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Done command to complete the task that existed in the list
     * @throws DukeCommandException if there is no tasks to be completed, invalid task index, an issue with saving the
     * task's completion or an issue with updating the task's completion status
     */
    @Override
    public void execute() throws DukeCommandException {
        if (taskManager.getTasksSize() == 0) {
            throw new DukeCommandException("done", String.valueOf(this.index), "There are no task to be completed.");
        } else if (index < 0 || index >= taskManager.getTasksSize()) {
            throw new DukeCommandException("done", String.valueOf(this.index), "Please enter a valid task index " +
                    "ranging from 1 to " + taskManager.getTasksSize() + " (inclusive).");
        } else if (taskManager.getTask(this.index).getStatusSymbol().equals("X")) {
            throw new DukeCommandException("done", String.valueOf(this.index), "This task is already completed.");
        } else {
            try {
                taskManager.completeTask(this.index);
                ui.printDoneMsg(this.index, taskManager.getTask(this.index));
                Storage.saveTasks(taskManager.getTasks());
            } catch(DukeException e) {
                throw new DukeCommandException("done", String.valueOf(index), e.getMessage());
            }
        }
    }
}
