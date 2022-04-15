import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Command to create a deadline object.
 */
public class DeadlineCommand extends Command {

    private String command;

    /**
     * Constructs a deadline command.
     *
     * @param command input command from user.
     */
    public DeadlineCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the Deadline command by creating a Deadline event.
     *
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If deadline command is missing description.
     * @throws DukeWrongInputException If user input is of the wrong format.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException, DukeIoException {
        String[] descriptionDeadlinePair = descriptionBuilder(command);
        String description = descriptionDeadlinePair[0];
        String deadline = descriptionDeadlinePair[1];
        if (command.equals("deadline")) {
            throw new DukeMissingInputException("OOPS! The description of a deadline cannot be empty.");
        }
        if (!isDate(deadline)) {
            throw new DukeWrongInputException(
                    "OOPS! Please enter your deadline in the valid format: deadline /by yyyy-mm-dd");
        }
        try {
            LocalDate dateDeadline = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Deadline newDeadline = new Deadline(description, dateDeadline);
            taskList.add(newDeadline);
            storage.save(taskList.getTaskList());
            return ui.showTaskAdded(newDeadline);
        } catch (DateTimeParseException e) {
            throw new DukeWrongInputException("OOPS! Please enter a valid yyyy-mm-dd.");
        } catch (IOException e) {
            throw new DukeIoException("File error: Could not save.");
        }
    }

    /**
     * Builds the description and deadline from user's deadline command.
     *
     * @param command user command.
     * @return a String array with task description at pos 0 and task deadline at pos 1.
     */
    public String[] descriptionBuilder(String command) {
        String[] commandArr = command.split(" ");
        String description = "";
        String deadline = "";
        assert commandArr.length == 1;
        boolean hasFoundBy = false;
        for (int i = 1; i < commandArr.length; i++) {
            if (commandArr[i].equals("/by")) {
                hasFoundBy = true;
                continue;
            }
            if (hasFoundBy) {
                deadline += (commandArr[i] + " ");
            } else {
                description += (commandArr[i] + " ");
            }
        }
        description = description.trim();
        deadline = deadline.trim();
        return new String[] {description, deadline};
    }

    /** Checks if a certain string is of date format.
     *
     * @param str - input string to be checked if it is in the format of a date.
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
}
