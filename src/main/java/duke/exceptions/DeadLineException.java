package duke.exceptions;

/**
 * Represents the exception from invalid creation of Deadline objects
 * due to wrong format being inputted
 */
public class DeadLineException extends DukeException {
    public DeadLineException() {
       super("Sorry Master, please key in the deadline task in the following format:\n"
             + "\'Deadline task name /by date(in YYYY-MM-DD HH:MM format)\'.");
    }

    @Override
    public String toString() {
        return "Error";
    }
}
