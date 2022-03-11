package todobeast.commands;

import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.TaskNotFoundException;
import todobeast.exceptions.ToDoBeastException;
import todobeast.tasks.Task;

public class AddNotesCommand extends Command {

    private final int taskNumber;
    private final String taskNotes;

    /**
     * Constructor for AddNotesCommand.
     * @param taskNumber the task number of the task for the notes to be added to.
     * @param taskNotes the notes to be added.
     */
    public AddNotesCommand(int taskNumber, String taskNotes) {
        this.taskNumber = taskNumber;
        this.taskNotes = taskNotes;
    }

    /**
     * Adds the task notes to the specified task
     * @param taskList the TaskList that belongs to this application
     * @param ui the Ui that belongs to this application
     * @throws ToDoBeastException if the task index provided is invalid.
     */
    public void execute(TaskList taskList, Ui ui) throws ToDoBeastException {
        if (!taskList.isTaskIndexInRange(taskNumber - 1)) {
            throw new TaskNotFoundException("Task with index " + taskNumber + " does not exist in the list!");
        }

        Task currentTask = taskList.getTask(taskNumber);

        if (taskNotes.length() == 0) {
            taskList.clearTaskNotes(currentTask);
            ui.addToResponseOutput(ui.showTaskNotesCleared(currentTask));
        } else {
            taskList.setTaskNotes(currentTask, taskNotes);
            ui.addToResponseOutput(ui.showTaskNotesAdded(currentTask));
        }

    }
}
