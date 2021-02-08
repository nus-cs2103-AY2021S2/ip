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
        updateOutput(task, tasks);
    }

    @Override
    protected void updateOutput(Task task, TaskList tasks) {
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
        boolean isSelectionOutOfBounds = Integer.parseInt(description) > tasks.getSize()
                || Integer.parseInt(description) <= 0;

        if (isSelectionOutOfBounds) {
            throw new DukeException(command, DukeExceptionType.SELECTION_EXCEED_RANGE);
        }
        doneProcess(description, tasks);
        storage.save(tasks);
    }

}
