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
@SuppressWarnings("checkstyle:Regexp")
public class AddTask extends Command {

    /**
     * AddTask Constructor
     *
     * @param command Task name
     * @param commandDetail Task details
     */
    public AddTask(String command, String commandDetail) {
        super.commandType = command;
        super.commandDetail = commandDetail;
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
            newTask = new Event(this.commandDetail,
                    LocalDate.parse(this.dateTime, DateTimeFormatter.ofPattern("MMM dd yyyy")));
            break;
        case "deadline":
            formatDateTime();
            newTask = new Deadline(this.commandDetail,
                    LocalDate.parse(this.dateTime, DateTimeFormatter.ofPattern("MMM dd yyyy")));
            break;
        case "todo":
            newTask = new ToDo(this.commandDetail);
            break;
        default:
            break;
        }
        taskList.addTask(newTask);

        String taskDetail = newTask.toString();
        int numTasks = taskList.getSize();
        this.outputMessage += "\t  " + taskDetail + "\n\t Now you have " + numTasks + " tasks in the list.";
    }

    private void formatDateTime() throws DukeException {
        String[] result = new String[2];
        boolean isEventTask = this.commandType.equals("event");
        boolean isDeadlineTask = this.commandType.equals("deadline");

        if (isEventTask) { // Guard clause
            result = this.commandDetail.trim().split(" /at ");
        }

        if (isDeadlineTask) { // Guard clause
            result = this.commandDetail.trim().split(" /by ");
        }

        this.dateTime = result[1];
        this.commandDetail = result[0];
        boolean isInvalidDateTime = !checkDateTime(this.dateTime);

        if (isInvalidDateTime) {
            throw new DukeException(ExceptionType.INVALID_DATETIME, "");
        }
    }

    private boolean checkDateTime(String dateTime) {
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
