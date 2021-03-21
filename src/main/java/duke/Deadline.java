package duke;

/**
 * A type of Task with a deadline.
 */
public class Deadline extends Task {
    public Deadline(String description, String date, String time) {
        super(description, date, time, "[D]", false);
    }
}
