package duke;

public class DukeException extends Exception {
    private final String exception;

    public DukeException(String exception) {
        super(exception);
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "Omo... I'm sorry..." + exception;
    }
}
