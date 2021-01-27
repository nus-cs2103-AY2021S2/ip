package percy.exception;

public class DeadlineException extends PercyException {
    public DeadlineException() {
    }

    @Override
    public String toString() {
        return " ☹ OOPS!!! The description of a deadline cannot be empty.";
    }
    }
