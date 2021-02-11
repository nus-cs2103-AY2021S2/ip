package duke.command;

import duke.bot.Storage;
import duke.exception.DukeCommandException;
import duke.exception.DukeException;

/** An executable command to set a task as completed */
public class DoneCommand extends Command {
    /** Index of the task that is going to be completed */
    private int index;

    /**
     * Constructor of a DoneCommand
     *
     * @param index Index of a task that will be completed
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Done command to complete the task that existed in the list and returns a response message
     *
     * @throws DukeCommandException if there is no tasks to be completed, invalid task index, an issue with saving the
     * task's completion or an issue with updating the task's completion status
     */
    @Override
    public String execute() throws DukeCommandException {
        if (taskManager.getTasksSize() == 0) {
            throw new DukeCommandException("done", String.valueOf(this.index), "There are no task to be completed.");
        } else if (index < 0 || index >= taskManager.getTasksSize()) {
            String msg = "Please enter a valid task index ranging from 1 to " + taskManager.getTasksSize()
                    + " (inclusive).";
            throw new DukeCommandException("done", String.valueOf(this.index), msg);
        } else if (taskManager.getTask(this.index).getStatusSymbol().equals("X")) {
            throw new DukeCommandException("done", String.valueOf(this.index), "This task is already completed.");
        }

        try {
            taskManager.completeTask(this.index);
            Storage.saveTasks(taskManager.getTasks());
            return ui.constructDoneMessage(this.index, taskManager.getTask(this.index));
        } catch (DukeException e) {
            throw new DukeCommandException("done", String.valueOf(index), e.getMessage());
        }
    }
}
