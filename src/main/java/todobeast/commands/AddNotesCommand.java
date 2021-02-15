package todobeast.commands;

import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.TaskNotFoundException;
import todobeast.exceptions.ToDoBeastException;
import todobeast.tasks.Task;

public class AddNotesCommand extends Command {

    private final int taskNumber;
    private final String taskNotes;

    public AddNotesCommand(int taskNumber, String taskNotes) {
        this.taskNumber = taskNumber;
        this.taskNotes = taskNotes;
    }

    public void execute(TaskList taskList, Ui ui) throws ToDoBeastException {
        if (!taskList.isTaskIndexInRange(taskNumber)) {
            throw new TaskNotFoundException("Task with index " + taskNumber + " does not exist in the list!");
        }

        Task currentTask = taskList.getTask(taskNumber);
        taskList.setTaskNotes(currentTask, taskNotes);
        ui.addToResponseOutput(ui.showTaskNotesAdded(currentTask));
    }
}
