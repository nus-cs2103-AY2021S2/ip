package tasks;

import static java.lang.Boolean.parseBoolean;

import datetime.KiwiDateTime;


/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final KiwiDateTime deadlineDateTime; // need a better name, but also cannot confuse with the class name

    /**
     * Creates a deadline object
     * @param desc description of the deadline object
     * @param deadlineDateTime date/time that the deadline task is due
     */
    public Deadline(String desc, KiwiDateTime deadlineDateTime) {
        super(desc);
        this.deadlineDateTime = deadlineDateTime;
    }

    // used when parsing deadline task from stored file
    private Deadline(String desc, KiwiDateTime deadlineDateTime, boolean isDone) {
        super(desc, isDone);
        this.deadlineDateTime = (deadlineDateTime);
    }

    /**
     * Returns a string representation of a deadline task
     * @return String representation of a deadline
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description
                + " (by: " + deadlineDateTime + ")";
    }

    @Override
    public String unparse() {
        return "D" + STORAGE_DELIMITER + description + STORAGE_DELIMITER + isDone
                + STORAGE_DELIMITER + deadlineDateTime.unparse() + System.lineSeparator();
    }


    /**
     * Creates a deadline object based on the string stored in the hard disk.
     * @param oneLine One line of stored input to be parsed into a deadline
     * @return tasks.Deadline Object
     */
    public static Deadline parse(String oneLine) {
        assert oneLine.startsWith("D" + STORAGE_DELIMITER);

        // split string into different fields
        String[] fields = oneLine.split(STORAGE_DELIMITER);
        assert fields.length == 3 + 1 : "deadline storage parser detecting fewer than needed fields";

        // fields for deadline object
        boolean isDone = parseBoolean(fields[2]);
        String desc = fields[1];
        KiwiDateTime dt = KiwiDateTime.parse(fields[3]);

        return new Deadline(desc, dt, isDone);
    }


}
