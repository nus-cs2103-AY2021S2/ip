package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * Constructor for deadltine task.
     *
     * @param description Name of Deadline task.
     * @param deadline Time format of deadline.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Static method to create a Deadline task.
     *
     * @param taskInfo information about the deadline task.
     * @return a deadline task.
     * @throws DukeWrongFormatException if info is wrongly formatted.
     * @throws DukeMissingDescriptionException if info is missing.
     */
    public static Deadline create(String taskInfo) throws DukeWrongFormatException,
            DukeMissingDescriptionException {
        String[] parsedInfo = taskInfo.split(" /by ", 2);
        if (parsedInfo.length != 2) {
            throw new DukeWrongFormatException("deadline");
        } else if (parsedInfo[0].equals(" ") || parsedInfo[1].equals(" ")) {
            throw new DukeMissingDescriptionException("deadline");
        } else {
            try {
                LocalDateTime ldt = Parser.parseInputDate(parsedInfo[1]);
                return new Deadline(parsedInfo[0], ldt);
            } catch (DateTimeParseException e) {
                throw new DukeWrongFormatException("deadline");
            }
        }
    }

    /**
     * Return string of deadline name and info.
     * Format is for saving task into text file.
     *
     * @return string format of task's info.
     */
    @Override
    public String saveTask() {
        return String.format("D | %s | %s | %s\n", super.getStatusIcon(),
                description, super.timeFormat(deadline));
    }

    /**
     * Returns string of deadline task name and info.
     * Format is for the display on the list.
     *
     * @return string format of task's info.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + super.timeFormat(deadline) + ")";
    }
}
