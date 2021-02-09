package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Handles deletion of task
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand
     *
     * @param command Delete command
     * @param selection Selected task
     */
    public DeleteCommand(String command, String selection) {
        this.command = command;
        this.description = selection;
        this.date = "";
    }

    private void deleteProcess(String selection, TaskList tasks) {
        int taskNum = Integer.parseInt(selection);
        assert taskNum >= 0 : "Negative integer supplied";
        Task task = tasks.get(taskNum);
        assert task != null : "Task is null";
        tasks.remove(task);
        output = "Noted. I've removed this task: \n"
                + task.toString() + getRemainingTasks(tasks);
    }

    private String getRemainingTasks(TaskList tasks) {
        return "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Deletes task from TaskList, saves to storage file and outputs response to terminal
     *
     * @param tasks TaskList
     * @param storage Storage instance
     * @throws DukeException If invalid selection given
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        if (Integer.parseInt(description) > tasks.size() || Integer.parseInt(description) <= 0) {
            // Selection out of taskList range
            throw new DukeException(command, DukeExceptionType.SELECTION_EXCEED_RANGE);
        }
        deleteProcess(description, tasks);
        assert storage != null : "Storage object not initialized";
        storage.save(tasks);
    }

    /**
     * Determines if Exit is called by user
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
