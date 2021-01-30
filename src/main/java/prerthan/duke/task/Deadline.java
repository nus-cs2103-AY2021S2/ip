package prerthan.duke.task;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import prerthan.duke.exception.DukeEmptyDetailException;
import prerthan.duke.exception.DukeInvalidDateException;
import prerthan.duke.parse.DateParser;

/**
 * A Deadline, with a date-time group when it is due.
 */
public class Deadline extends Task {
    private ZonedDateTime by;

    Deadline(String detail, ZonedDateTime by) throws DukeEmptyDetailException {
        super(detail);
        this.by = by;
    }

    /**
     * Creates a deadline with a specified detail, and a date-time to be done by.
     * 
     * @param detail the deadline detail
     * @param by     the date-time to be done by
     * @throws DukeEmptyDetailException if the detail is empty.
     * @throws DukeInvalidDateException
     */
    public Deadline(String detail, String by) throws DukeEmptyDetailException, DukeInvalidDateException {
        this(detail, DateParser.parseDateTimeString(by));
    }

    @Override
    public char getTaskTypeIcon() {
        return 'D';
    }

    @Override
    public String toString() {
        return String.format("[%c]%s (by: %s)", this.getTaskTypeIcon(), super.toString(), this.by);
    }

    @Override
    public String encode() {
        return String.format("[%c]%s (due on: %s)", this.getTaskTypeIcon(), super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("dd/MMM/uuuu hh:mma z")));
    }

    // @Override
    // public String toString()
    // {
    // return String.format("[%c]%s (by: %s)", this.getTaskTypeIcon(),
    // super.toString(),
    // this.byString);
    // }
}