package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * AddTask handles the addition of event, deadline and todo tasks to the list only
 */
public class AddTask extends Command {

    /**
     * AddTask Constructor
     *
     * @param command Task name
     * @param commandDetails Task details
     */
    public AddTask(String command, String commandDetails) {
        super.commandType = command;
        super.commandDetails = commandDetails;
        super.dateTime = "";
        super.outputMessage = "";
        // index is -1 because it is only used in done and delete tasks
        super.index = -1;
    }

    private void handleNewTask(TaskList taskList) throws DukeException {
        Task newTask = null;
        this.outputMessage = "Got it. I've added this task: \n";

        switch (this.commandType) {
        case "event":
            formatDateTime();
            newTask = new Event(this.commandDetails,
                    LocalDate.parse(this.dateTime, DateTimeFormatter.ofPattern("MMM dd yyyy")));
            break;
        case "deadline":
            formatDateTime();
            newTask = new Deadline(this.commandDetails,
                    LocalDate.parse(this.dateTime, DateTimeFormatter.ofPattern("MMM dd yyyy")));
            break;
        case "todo":
            newTask = new ToDo(this.commandDetails);
            break;
        default:
            break;
        }
        taskList.add(newTask);

        this.outputMessage += "\t  " + newTask.toString() + "\n\t Now you have "
                + taskList.size() + " tasks in the list.";
    }

    private void formatDateTime() throws DukeException {
        String[] result;

        if (this.commandType.equals("event")) {
            result = this.commandDetails.trim().split(" /at ");
        } else {
            result = this.commandDetails.trim().split(" /by ");
        }

        this.dateTime = result[1];
        this.commandDetails = result[0];

        if (!validDateTime(this.dateTime)) {
            throw new DukeException(ExceptionType.INVALID_DATETIME, "");
        }
    }

    private boolean validDateTime(String dateTime) {
        try {
            LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    /**
     * Adds task to TaskList, save the updated TaskList into data file and output message to the command line
     *
     * @param tasks TaskList
     * @param storage Instance of Storage
     * @throws DukeException If the input is invalid date format
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        handleNewTask(tasks);
        storage.saveData(tasks);
    }
}
