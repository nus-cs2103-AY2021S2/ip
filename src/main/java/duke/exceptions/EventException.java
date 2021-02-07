package duke.exceptions;

public class EventException extends DukeException {
    public EventException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "change toString()";
    }
}
