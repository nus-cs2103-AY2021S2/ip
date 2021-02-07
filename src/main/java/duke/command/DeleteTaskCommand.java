package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteTaskCommand extends Command {
    private static final String NO_TASK_INDEX_ERROR_MESSAGE = "I'm not sure which task you want to delete!";
    private static final String INDEX_NOT_NUMBER_ERROR_MESSAGE = "I can only find a task with its index number.";
    private static final String INDEX_OOB_ERROR_MESSAGE = "I cannot find the task you are referring to.";
    private static Task mostRecentDeletedTask;

    private int taskIndex;

    public DeleteTaskCommand(String taskIndex) throws DukeException {
        if (taskIndex.isBlank()) {
            throw new DukeException(NO_TASK_INDEX_ERROR_MESSAGE);
        }
        try {
            assert !taskIndex.isBlank() : NO_TASK_INDEX_ERROR_MESSAGE;
            this.taskIndex = Integer.parseInt(taskIndex.trim());
        } catch (NumberFormatException e) {
            throw new DukeException(INDEX_NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTask(taskIndex - 1);
            mostRecentDeletedTask = task;
            tasks.deleteTask(taskIndex - 1);
            storage.setMostRecentCommand(this);
            storage.saveTasksToFile(tasks);
            return ui.getDeleteTaskMessage(task, tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INDEX_OOB_ERROR_MESSAGE);
        }
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(mostRecentDeletedTask);
        storage.saveTasksToFile(tasks);
        return ui.getUndoDeleteTaskMessage(mostRecentDeletedTask, tasks);
    }


    @Override
    public boolean isExitCommand() {
        return false;
    }
}
