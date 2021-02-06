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

    private String type;
    private LocalDate dueDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor to create add command object
     */
    public AddCommand(String input, String type, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(input);
        this.type = type;
        this.dueDate = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /** Adds task into task list and return task list
     * @return TaskList
     * @throws DukeException
     */
    public String execute() throws DukeException {
        boolean executedUnSuccessfully = false;
        switch (type) {
        case ("todo"):
            executedUnSuccessfully = tasklist.addToDo(input);
            break;
        case ("deadlines"):
            executedUnSuccessfully = tasklist.addDeadline(this.input, dueDate, startTime);
            break;
        case ("events"):
            executedUnSuccessfully = tasklist.addEvent(this.input, dueDate, startTime, endTime);
            break;
        default:
            break;
        }

        int currentSize = tasklist.getTaskListArray().size()-1;
        DataStorage.save(tasklist.getTaskListArray());

        System.out.println(executedUnSuccessfully);
        System.out.println(input);
        if(executedUnSuccessfully){
            return ui.printDuplicateMessage();
        }else{
            return ui.displayAddedTaskMessage(tasklist.getTask(currentSize), currentSize+1);
        }

    }
}
