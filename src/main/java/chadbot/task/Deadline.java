package chadbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a single deadline created by the user via user input to the Duke program.
 * It contains functions which enable the user to mark the task as done,
 * and a date which the deadline is due.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class Deadline extends Task implements Comparable<Deadline> {
    /** Date which the deadline is due. */
    private LocalDate date;

    /**
     * Default constructor for the Deadline class.
     *
     * @param name Description of the deadline.
     * @param date Date which the deadline is due.
     */
    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;

        assert(this.name != null && this.date != null);
    }

    /**
     * Returns the date the Deadline is due on, formatted as a String.
     *
     * @return The date the Deadline is due on, formatted as a String.
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * Returns the date the Deadline is due on, formatted as a LocalDate.
     *
     * @return The date the Deadline is due on, formatted as a LocalDate.
     */
    public LocalDate getDateAsLocalDate() {
        return date;
    }

    /**
     * Modifies the date the Deadline is due on.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns a description of the deadline, formatted with its type,
     * followed by an "X" if it has been marked as done.
     *
     * @return Formatted description of the deadline.
     */
    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + name + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Compares this deadline with another deadline, by comparing their due dates.
     *
     * @param other The deadline that is being compared to.
     * @return The difference between this deadline and the deadline that is being compared to, in terms of days.
     */
    @Override
    public int compareTo(Deadline other) {
        return date.compareTo(other.getDateAsLocalDate());
    }

}
