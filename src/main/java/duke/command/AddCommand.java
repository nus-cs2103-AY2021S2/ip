package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.data.DataStorage;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Create add command class
 */

public class AddCommand extends Command {

    /**
     * Constructor to create add command object
     */
    public AddCommand(String input, TaskList taskList) {
        super(input, taskList);
    }

    /** Add task into task list and return task list
     * @param tasklist
     * @param ui
     * @param storage
     * @param type
     * @param dueDate
     * @param startTime
     * @param endTime
     * @return TaskList
     * @throws DukeException
     */
    public TaskList execute(TaskList tasklist, UI ui, DataStorage storage, String type, LocalDate dueDate,
                            LocalTime startTime, LocalTime endTime) throws DukeException {
        switch (type) {
        case ("todo"):
            tasklist.addToDo(this.input);
            break;
        case ("deadlines"):
            System.out.println("hehe " + this.input);
            tasklist.addDeadline(this.input, dueDate, startTime);
            break;
        case ("events"):
            tasklist.addEvent(this.input, dueDate, startTime, endTime);
            break;
        default:
            break;
        }

        ArrayList<Task> taskArrayList = tasklist.getTaskListArray();
        int currentSize = taskArrayList.size();

        ui.displayAddedTaskMessage(tasklist.getTask(currentSize - 1), currentSize);
        storage.save(tasklist.getTaskListArray());

        return tasklist;
    }

}
