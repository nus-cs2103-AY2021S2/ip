package duke.command;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

public class DeadlineCommand implements Command {
    private ArrayList<String> eventDescription;
    public DeadlineCommand(ArrayList<String> a) {
        eventDescription = a;
    }

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        try {
            Task deadlineTask = dukeTaskList.addDeadlineTask(eventDescription);
            dukeUi.showAddedTask(deadlineTask, dukeTaskList.getNumberOfTasks());
        } catch (DukeException e) {
            dukeUi.showErrorMsg(e.getMessage());
        }
    }
}
