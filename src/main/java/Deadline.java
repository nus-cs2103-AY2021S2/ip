import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;

/**
 * Represents a Deadline task. Represented by the task, the LocalDate object of the deadline,
 * and a boolean state denoting whether the task has been done or not.
 */

public class Deadline extends Task {
    private static final String OUTPUT_DATE_FORMAT = "MMM dd yyyy";
    private final LocalDate deadline;

    /**
     * Constructor of a Deadline object
     * @param task Task need to be done
     * @param deadline Date when the task need to be done
     * @throws DukeExceptionDeadline The date format is not in "yyyy-MM-dd"
     */
    Deadline(String task, String deadline) throws DukeExceptionDeadline {
        super(task);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            this.deadline = LocalDate.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            throw new DukeExceptionDeadline("Wrong format of date." +
                    " The format should be yyyy-MM-dd");
        }
    }

    /**
     * Constructor of a deadline object
     * @param task Task need to be done
     * @param deadline Date when the task need to be done
     * @param done A boolean representing the state of the task (done or not)
     * @throws DukeExceptionDeadline The date format is not in "yyyy-MM-dd"
     */
    Deadline(String task, String deadline, boolean done) throws DukeExceptionDeadline {
        super(task, done);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            this.deadline = LocalDate.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeExceptionDeadline("Wrong format of date. " +
                    "The format should be yyyy-mm-dd");
        }
    }

    /**
     * Constructor of a deadline Object
     * @param task Task need to be done
     * @param deadline Date when the task need to be done
     * @param done A boolean representing the state of the task (done or not)
     */
    Deadline(String task, LocalDate deadline, boolean done) {
        super(task, done);
        this.deadline = deadline;
    }

    /**
     * Overriden method from the parent class Task. The purpose is to
     * return a new done Task.
     * @return a Task that has been done.
     */
    @Override
    public Task finishTask() throws DukeException{
        if (this.isDone) {
            throw new DukeException("Task has been done before!");
        }
        return new Deadline(this.task, this.deadline,true);
    }

    /**
     * An overriden method from the parent class Task. The purpose is to
     * return a string representation for txt files.
     * @return String representation for txt files.
     */
    @Override
    public String saveString() {
        return "D|" + super.saveString() + "|" + this.deadline;
    }

    /**
     * Overriden method from the object class.
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        String date = this.deadline.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
