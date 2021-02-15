package moomin.command;

import moomin.exception.DukeException;
import moomin.storage.Storage;
import moomin.task.Task;
import moomin.task.TaskList;
import moomin.ui.Ui;

public class MarkTaskAsDoneCommand extends Command {
    private static final String NO_TASK_INDEX_ERROR_MESSAGE = "I'm not sure which task you want to mark as done!";
    private static final String INDEX_NOT_NUMBER_ERROR_MESSAGE = "I can only find a task with its index number.";
    private static final String INDEX_OOB_ERROR_MESSAGE = "I cannot find the task you are referring to.";
    private static Task mostRecentEditedTask;

    private int taskIndex;

    public MarkTaskAsDoneCommand(String taskIndex) throws DukeException {
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
            mostRecentEditedTask = task;
            boolean taskWasDone = task.isDone();
            task.markAsDone();
            storage.setMostRecentCommand(this);
            storage.saveTasksToFile(tasks);
            return ui.getMarkTaskAsDoneMessage(task, taskWasDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INDEX_OOB_ERROR_MESSAGE);
        }
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            mostRecentEditedTask.markAsNotDone();
            storage.saveTasksToFile(tasks);
            return ui.getUndoMarkAsDoneMessage(mostRecentEditedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INDEX_OOB_ERROR_MESSAGE);
        }
    }


    @Override
    public boolean isExitCommand() {
        return false;
    }
}
