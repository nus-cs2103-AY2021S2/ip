import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddTask extends Command {

    AddTask(String command, String commandDetails) {
        super.commandType = command;
        super.commandDetails = commandDetails;
        super.dateTime = "";
        super.outputMessage = "";
        super.index = -1;
    }

    public void handleNewTask(TaskList taskList) throws DukeException {
        Task newTask = null;
        this.outputMessage = " Got it. I've added this task: \n";

        switch (commandType) {
        case "event":
            formatDateTime();
            newTask = new Event(this.commandDetails, LocalDate.parse(this.dateTime,
                    DateTimeFormatter.ofPattern("MMM dd yyyy")));
            break;
        case "deadline":
            formatDateTime();
            newTask = new Deadline(this.commandDetails, LocalDate.parse(this.dateTime,
                    DateTimeFormatter.ofPattern("MMM dd yyyy")));
            break;
        case "todo":
            newTask = new ToDo(this.commandDetails);
            break;
        default:
            break;
        }
        taskList.add(newTask);
        if(newTask != null) {
            this.outputMessage += "\t  " + newTask.toString() + "\n\t Now you have "
                    + taskList.size() + " tasks in the list.";
        }
    }

    public void formatDateTime() throws DukeException {
        String[] result;

        if (commandType.equals("event")) {
            result = commandDetails.trim().split(" /at ");
        } else {
            result = commandDetails.trim().split(" /by ");
        }
        this.dateTime = result[1];
        this.commandDetails = result[0];

        if(!validDateTime(this.dateTime)) {
            throw new DukeException(ExceptionType.INVALID_DATETIME, "");
        }
    }

    public boolean validDateTime(String dateTime) {
        try
        {
            LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        catch (DateTimeParseException ex)
        {
            return false;
        }
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.commandDetails.equals("")) {
            throw new DukeException(ExceptionType.BLANK_DESCRIPTION, this.commandType);
        }
        handleNewTask(taskList);
        storage.saveData(taskList);
        ui.display(this.outputMessage);
    }

    @Override
    public boolean continueInput() {
        return true;
    }
}