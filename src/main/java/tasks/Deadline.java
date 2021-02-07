package tasks;

import datetime.ParseDateTime;

import static java.lang.Boolean.parseBoolean;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime deadlineDateTime; // need a better name, but also cannot confuse with the class name

    /**
     * Creates a deadline object
     * @param desc description of the deadline object
     * @param deadlineDateTime date/time that the deadline task is due
     */
    public Deadline(String desc, String deadlineDateTime) {
        super(desc);
        this.deadlineDateTime = ParseDateTime.parse(deadlineDateTime);
    }

    private Deadline(String desc, String deadlineDateTime, boolean isDone) {
        super(desc, isDone);
        this.deadlineDateTime = ParseDateTime.parse(deadlineDateTime);
    }

    /**
     * Returns a string representation of a deadline task
     * @return String representation of a deadline
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description
                + " (by: " + ParseDateTime.readableString(deadlineDateTime) + ")";
    }

    @Override
    public String unparse() {
        return "D" + delimiter + description + delimiter + isDone
                + delimiter + ParseDateTime.unparse(deadlineDateTime) + System.lineSeparator();
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
        assert oneLine.startsWith("T" + delimiter);

        int descStartIdx = oneLine.indexOf(delimiter);
        int descEndIdx = oneLine.indexOf(delimiter, descStartIdx + 1);
        String desc = oneLine.substring(descStartIdx + delimiter.length(), descEndIdx);

        int doneEndIdx = oneLine.indexOf(delimiter, descEndIdx + 1);
        String doneStr = oneLine.substring(descEndIdx + delimiter.length(), doneEndIdx);
        boolean isDone = parseBoolean(doneStr);


        String deadline = oneLine.substring(doneEndIdx + delimiter.length());

        return new Deadline(desc, deadline, isDone);
    }

    /**
     * This method only exists for one-off testing of this deadline class
     * @param args
     */
    public static void main(String[] args) {
        Deadline t = new Deadline("hello world", "7am on wed");
        System.out.println(t);
        t.markAsDone();
        System.out.println(t);
        System.out.println(t.unparse());
        System.out.println(parse(t.unparse()));
        // test with other cases without relying on t
    }


}
