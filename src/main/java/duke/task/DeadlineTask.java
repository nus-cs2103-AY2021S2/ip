package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {

    protected String deadline;

    /**
     * Creates a new instance of <code>DeadlineTask</code>.
     *
     * @param description Description of deadline task.
     * @param deadline Deadline of deadline task.
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns date of deadline.
     *
     * @return Date of deadline.
     */
    public LocalDate getDeadlineDate() throws DateTimeParseException {
        String[] deadlineArr = this.deadline.split(" ");
        String deadlineDateString = deadlineArr[0];
        LocalDate deadlineDate = LocalDate.parse(deadlineDateString);
        return deadlineDate;
    }

    /**
     * Returns time of deadline.
     *
     * @return Time of deadline.
     */
    public LocalTime getDeadlineTime() throws DateTimeParseException {
        String[] deadlineArr = this.deadline.split(" ");
        String deadlineTimeString = deadlineArr[1];
        LocalTime deadlineTime = LocalTime.parse(deadlineTimeString);
        return deadlineTime;
    }

    /**
     * Checks if user input has the <code>/by</code> keyword.
     *
     * @param description String input given by user after <code>deadline</code>.
     * @return True if user input has the <code>/by</code> keyword.
     */
    public static boolean hasByKeyword(String description) {
        String[] descriptionArr = description.split(" /by ");
        return descriptionArr.length != 1;
    }

    /**
     * Checks if user input has the correct date & time format.
     *
     * @param description String input given by user after <code>deadline</code>.
     * @return True if user input has the correct date & time format.
     */
    public static boolean hasCorrectDateTimeFormat(String description) {
        String[] descriptionArr = description.split(" /by ");

        Pattern correctDateTimePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$");
        String inputDateTimeString = descriptionArr[1];
        Matcher matcher = correctDateTimePattern.matcher(inputDateTimeString);

        return matcher.find();
    }

    /**
     * Checks if user input has the correct format.
     *
     * @param description String input given by user after <code>deadline</code>.
     * @throws DukeException If user input does not have the correct format.
     */
    public static void checkFormat(String description) throws DukeException {
        if (!DeadlineTask.hasByKeyword(description)) {
            throw new DukeException("Your description is not given in the correct format!");
        } else if (!DeadlineTask.hasCorrectDateTimeFormat(description)) {
            throw new DukeException("Your deadline is given in the wrong format! "
                    + "Please make sure it is in the following format: YYYY-MM-DD HH:MM");
        }
    }


    /**
     * Returns String representation of deadline task.
     * @return String representation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + this.description
                + " (by: " + this.getDeadlineDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.getDeadlineTime().format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }
}
