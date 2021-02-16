package duke.exception;

public class DukeValidationException extends DukeException {

    private String field;

    public DukeValidationException(String field, String message) {
        super(message);
        this.field = field;
    }

    @Override
    public String toString() {
        return "ALAMAK! You've given the wrong input for: " + field + ", " + getMessage();
    }
}
