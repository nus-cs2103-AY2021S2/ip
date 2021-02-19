package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.data.DataStorage;
import duke.exception.DukeException;

/**
 * Create add command class
 */

public class AddCommand extends Command {

    private String type;
    private LocalDate dueDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Create an add command
     */
    public AddCommand(String input, String type, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(input);
        this.type = type;
        this.dueDate = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Adds a task into the task list
     * @return TaskList
     * @throws DukeException
     */
    public String execute() throws DukeException {
        boolean executedUnsuccessfully = false;
        switch (type) {
        case ("todo"):
            executedUnsuccessfully = tasklist.addToDo(input);
            break;
        case ("deadline"):
            executedUnsuccessfully = tasklist.addDeadline(this.input, dueDate, startTime);
            break;
        case ("event"):
            executedUnsuccessfully = tasklist.addEvent(this.input, dueDate, startTime, endTime);
            break;
        default:
            break;
        }

        int currentSize = tasklist.getTaskListArray().size() - 1;
        DataStorage.save(tasklist.getTaskListArray());

        if (executedUnsuccessfully) {
            return ui.displayDuplicatedMessage();
        } else {
            return ui.displayAddedTaskMessage(tasklist.getTask(currentSize), currentSize + 1);
        }

    }
}
