import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Command to create a deadline object.
 */
public class DeadlineCommand extends Command {

    private String command;

    /**
     * Constructor method.
     * @param command input command from user.
     */
    public DeadlineCommand(String command) {
        this.command = command;
    }

    /**
     * Execute method for deadline.
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If deadline command is missing description.
     * @throws DukeWrongInputException If user input is not any of the commands available.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException {
        String description = "";
        String deadline = "";
        boolean foundBy = false;
        String[] commandArr = command.trim().split(" ");
        if (command.equals("deadline")) {
            throw new DukeMissingInputException("OOPS!!! The description of a deadline cannot be empty.");
        } else {
            for (int i = 1; i < commandArr.length; i++) {
                if (commandArr[i].equals("/by")) {
                    foundBy = true;
                    continue;
                }
                if (foundBy) {
                    deadline += (commandArr[i] + " ");
                } else {
                    description += (commandArr[i] + " ");
                }
            }
        }
        deadline = deadline.trim();
        if (isDate(deadline)) {
            LocalDate dateDeadline = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Deadline newDeadline = new Deadline(description, dateDeadline);
            taskList.add(newDeadline);
            ui.showTaskAdded(newDeadline);
        } else {
            throw new DukeWrongInputException(
                    "OOPS!!! Please enter your deadline in the format: deadline /by yyyy-mm-dd :-(");
        }
    }

    /** Method to check if a certain string is of date format.
     * @param str - input string to be checked if it is in the format of a string.
     * @return boolean value telling us whether the string is a date or just simple text.
     */
    public static boolean isDate(String str) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-DD");
        try {
            LocalDate.parse(str, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Indicates whether command is an exit command.
     * @return boolean value for whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
