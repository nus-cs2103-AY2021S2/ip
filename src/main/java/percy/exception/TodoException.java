package percy.exception;

public class TodoException extends PercyException {
    public TodoException() {
    }

    @Override
    public String toString() {
        return " ☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
