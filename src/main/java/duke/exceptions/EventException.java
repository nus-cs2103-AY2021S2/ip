package duke.exceptions;

/**
 * Represents the exception from invalid creation of Event objects
 * due to wrong format being inputted
 */
public class EventException extends DukeException {
    public EventException(String error) {
        super("Sorry Master, please key in the deadline task in the following format:\n"
                + "\'Event task name /at location date(in YYYY-MM-DD HH:MM format)\'.");
    }

    @Override
    public String toString() {
        return "Error";
    }
}
