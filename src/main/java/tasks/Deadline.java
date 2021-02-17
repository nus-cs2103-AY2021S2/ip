package tasks;

import datetime.KiwiDateTime;

import static java.lang.Boolean.parseBoolean;

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

    // note that this parsing is different from parsing user inputs.
    // since this parsing for hard disk storage is separate from that parsing,
    // the /at /by style of inputs won't affect this if they change, i think
    /**
     * Creates a deadline object based on the string stored in the hard disk.
     * @param oneLine One line of stored input to be parsed into a deadline
     * @return tasks.Deadline Object
     */
    public static Deadline parse(String oneLine) {

        // some repetition in this function across all types of tasks but abstracting them might be costly
        assert oneLine.startsWith("D" + STORAGE_DELIMITER);

        // fixme - refer to event method equivalent to fix this portion

        // split string into different fields
        String[] fields = oneLine.split(STORAGE_DELIMITER);
        assert fields.length == 3 + 1 : "storage parser detecting fewer than needed event arguments";

        // fields for deadline object
        boolean isDone = parseBoolean(fields[2]);
        String desc = fields[1];
        KiwiDateTime dt = KiwiDateTime.parse(fields[3]);

        return new Deadline(desc, dt, isDone);
    }

    /**
     * This method only exists for one-off testing of this deadline class
     * @param args
     */
    public static void main(String[] args) {
//        Deadline t = new Deadline("hello world", "7am on wed");
//        System.out.println(t);
//        t.markAsDone();
//        System.out.println(t);
//        System.out.println(t.unparse());
//        System.out.println(parse(t.unparse()));
        // test with other cases without relying on t
    }


}
