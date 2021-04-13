package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A sub-type of Task, deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructs new deadline task.
     *
     * @param description Name of Deadline task.
     * @param deadline Time format of deadline.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns new deadline task created with taskInfo.
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
                String deadlineName = parsedInfo[0];
                LocalDateTime deadline = Parser.parseInputDate(parsedInfo[1]);
                return new Deadline(deadlineName, deadline);
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
                description, super.formatTime(deadline));
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
                + super.formatTime(deadline) + ")";
    }
}

