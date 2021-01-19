package main.java.subfiles;

/**
 * The Deadline class represents a single deadline created by
 * the user via user input to the Duke program. It contains
 * functions which enable the user to mark the task as done,
 * and a date which the deadline is due.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class Deadline extends Task {
    /** Date which the deadline is due */
    private String date;

    public Deadline(String s, String t) {
        super(s);
        date = t;
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + name + " (by: " + date + ")";
    }

}
