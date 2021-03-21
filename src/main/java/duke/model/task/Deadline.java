package duke.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import duke.model.exception.DukeException;

/**
 * Represents the deadline type task that has a deadline date and an optional parsedDate
 * if provided date string is parsable
 */
public class Deadline extends ListItem {
    private final String date;
    private Optional<LocalDate> parsedDate;

    /**
     * Constructor for Deadline that was not provided the task done status
     *
     * @param task      takes in a string and pass to parent's constructor as task name
     * @param inputDate the deadline input by user
     */
    public Deadline(String task, String inputDate) {
        super(task);
        this.date = inputDate;
        this.parsedDate = Optional.ofNullable(parseDate(inputDate));
    }

    /**
     * the overloaded constructor that allows taking the status of the task
     *
     * @param task      takes in string and pass to parent's constructor as the task name
     * @param inputDate date entered as the deadline
     * @param isDone    the status of the task
     */
    public Deadline(String task, String inputDate, boolean isDone) {
        super(task, isDone);
        this.date = inputDate;
        this.parsedDate = Optional.ofNullable(parseDate(inputDate));
    }

    /**
     * the overloaded constructor that allows taking both the status of the task and tags (for loading from file)
     * @param task      takes in string and pass to parent's constructor as the task name
     * @param inputDate date entered as the deadline
     * @param isDone    the status of the task
     * @param inputTagList the list containing all the tags
     */
    public Deadline(String task, String inputDate, boolean isDone, List<String> inputTagList) {
        super(task, isDone, inputTagList);
        this.date = inputDate;
        this.parsedDate = Optional.ofNullable(parseDate(inputDate));
    }

    /**
     * Changes the task's status to be done
     *
     * @return the task to replace the old one in the list or to be used later
     */
    @Override
    public ListItem markAsDone() {
        return new Deadline(super.getTask(), (parsedDate.isEmpty()
                ? this.date : parsedDate.get().format(DateTimeFormatter.ofPattern("MMM d yyyy"))), true);
    }

    /**
     * print <code>Deadline</code> in specific format `[D][ ] taskName (by: DATE)`
     * DATE is chosen between <code>date</code> and <code>parsedDate</code>
     * depending on whether the parsedDate is empty or not
     */
    @Override
    public String toString() {
        return "[D]" + (super.isDone() == true ? "[X] " : "[ ] ") + super.getTask()
                + " (by: "
                + (parsedDate.isEmpty()
                ? this.date : parsedDate.get().format(DateTimeFormatter.ofPattern("dd MMM uuuu"))) + ")"
                + super.printTags();
    }

    /**
     * @return either the provided date or a parsed date to be printed
     */
    public String getDate() {
        return "|" + (parsedDate.isEmpty() ? this.date
                : parsedDate.get().format(DateTimeFormatter.ofPattern("dd MMM uuuu")));
    }

    /**
     * Check whether the date provide by the user is parsable and store it accordingly
     *
     * @param input the date given by the user
     * @return either a null or LocalDate that has a parsed date
     */
    public LocalDate parseDate(String input) {
        try {
            LocalDate parsedDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd MMM uuuu"));
            // check the deadline is not before the moment this is created
            return parsedDate;
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    /**
     * check if the provided parsable date is actually after today
     * @throws DukeException.DeadlineEarlierThanNowException
     */
    public boolean isValidDateLaterThanToday() throws DukeException.DeadlineEarlierThanNowException {
        if (this.parsedDate.isPresent()) {
            if (!parsedDate.get().isAfter(LocalDate.now())) {
                throw new DukeException.DeadlineEarlierThanNowException();
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
