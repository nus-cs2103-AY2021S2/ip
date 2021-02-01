package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the deadline type task that has a deadline date and an optional parsedDate
 * if provided date string is parsable
 */
public class Deadline extends ListItem {
    private final String date;
    private LocalDate parsedDate;

    /**
     * Constructor for Deadline that was not provided the task done status
     * @param task takes in a string and pass to parent's constructor as task name
     * @param inputDate the deadline input by user
     */
    public Deadline(String task, String inputDate) {
        super(task);
        this.date = inputDate;
        this.parsedDate = parseDate(inputDate);
    }

    /**
     * the overloaded constructor that allows taking the status of the task
     * @param task takes in string and pass to parent's constructor as the task name
     * @param inputDate date entered as the deadline
     * @param isDone the status of the task
     */
    public Deadline(String task, String inputDate, boolean isDone) {
        super(task, isDone);
        this.date = inputDate;
        this.parsedDate = parseDate(inputDate);
    }

    /**
     * Changes the task's status to be done
     * @return the task to replace the old one in the list or to be used later
     */
    @Override
    public ListItem markAsDone() {
        return new Deadline(super.getTask(), (parsedDate == null
                ? this.date : parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))), true);
    }

    @Override
    public String toString() {
        return "[D]" + (super.getDone() == true ? "[X] " : "[ ] ") + super.getTask()
                + " (by: "
                + (parsedDate == null
                ? this.date : parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) + ")";
    }

    /**
     * @return either the provided date or a parsed date to be printed
     */
    public String getDate() {
        return "|" + (parsedDate == null ? this.date : parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Check whether the date provide by the user is parsable and store it accordingly
     * @param input the date given by the user
     * @return eithe a null or LocalDate that has a parsed date
     */
    public LocalDate parseDate(String input) {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }
}
