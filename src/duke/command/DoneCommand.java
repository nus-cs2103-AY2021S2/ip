package duke.command;

import duke.exceptions.DukeException;
import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

public class DoneCommand implements Command {
    private int taskDoneInt;
    public DoneCommand(int i) {
        taskDoneInt = i;
    }
    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        try {
            Task doneTask = dukeTaskList.checkTaskAsDone(taskDoneInt);
            dukeUi.showTaskDone(doneTask);
        } catch (DukeException e) {
            dukeUi.showErrorMsg(e.getMessage());
        }
    }
}
