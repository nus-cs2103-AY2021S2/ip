package duke;

import java.time.LocalDate;

/**
 * The Deadline class inherits from Task and allows
 * for date arguments to be parsed.
 *
 * @author  Justin Gnoh
 * @version 1.0
 * @since   2021-02-06
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a Deadline Task with name and "by" attribute
     *
     * @param name This is the description of the task
     * @param by This is the deadline of the task
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Creates a Deadline Task with specific completion status
     *
     * @param name This is the description of the task
     * @param by This is the deadline of the task
     * @param isDone This is the status of the task
     */
    public Deadline(String name, LocalDate by, Boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * This method is used to process a given date format into
     * a desired format.
     *
     * @param originalDate This receives an unparsed date
     * @return String This returns a String date in "MMM dd yyyy" format
     */
    public String processDate(LocalDate originalDate) {
        // LocalDate date = LocalDate.parse(originalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String month = originalDate.getMonth().toString();
        String threeLetteredMonth = month.substring(0, 1) + month.substring(1, 3).toLowerCase();
        String day = Integer.toString(originalDate.getDayOfMonth());
        String year = Integer.toString(originalDate.getYear());

        String result = threeLetteredMonth + " " + day + " " + year;

        return result;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + processDate(by) + ")";
    }
}
