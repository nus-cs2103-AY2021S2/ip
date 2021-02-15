package duke.command;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

/**
 * Deadline Command, which adds a deadline task to Duke's TaskList when executed
 */

public class DeadlineCommand implements Command {
    private final ArrayList<String> eventDescription;

    /**
     * Constucts a DeadlineCommand object
     *
     * @param a Arraylist where index 0 contains the description of the deadline and index 1
     *          is the by date of the deadline
     */
    public DeadlineCommand(ArrayList<String> a) {
        eventDescription = a;
    }
    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) throws DukeException {
        try {
            Task deadlineTask = dukeTaskList.addDeadlineTask(eventDescription);
            dukeUi.showAddedTask(deadlineTask, dukeTaskList.getNumberOfTasks());
        } catch (DukeException e) {
            throw e;
        }
    }
    @Override
    public String executeGui(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) throws DukeException {
        try {
            Task deadlineTask = dukeTaskList.addDeadlineTask(eventDescription);
            return dukeUi.returnAddedTask(deadlineTask, dukeTaskList.getNumberOfTasks());
        } catch (DukeException e) {
            throw e;
        }
    }
}
