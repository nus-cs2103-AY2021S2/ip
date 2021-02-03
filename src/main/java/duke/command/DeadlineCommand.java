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
    private ArrayList<String> eventDescription;

    /**
     * Constuct a DeadlineCommand object
     * @param a Arraylist where index 0 contains the description of the deadline and index 1
     *          is the by date of the deadline
     */
    public DeadlineCommand(ArrayList<String> a) {
        eventDescription = a;
    }

    /**
     * Executes the deadline command, which adds a deadline task to Duke's TaskList
     * @param dukeTaskList give dukeTaskList
     * @param dukeUi give dukeUi
     * @param dukeStorage give dukeStorage
     */
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
