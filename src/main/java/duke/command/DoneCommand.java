package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Handles marking a task as done
 */
public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand
     *
     * @param command Done command
     * @param selection Selected task
     */
    public DoneCommand(String command, String selection) {
        this.command = command;
        this.description = selection;
        this.date = "";
    }

    private void doneProcess(String selection, TaskList tasks) {
        int taskNum = Integer.parseInt(selection);
        Task task = tasks.get(taskNum);
        task.markAsDone();
        output = "Nice! I've marked this task as done:\n\t  "
                + task.toString();
    }

    /**
     * Marks task as done TaskList, saves to storage file and outputs response to terminal
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
        doneProcess(description, tasks);
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
