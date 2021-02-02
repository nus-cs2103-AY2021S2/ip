package jaryl.duke;

/**
 * DukeException handles duke exceptions
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        Output output = new Output();
        return "☹ OOPS! " + this.getMessage();
    }
}
