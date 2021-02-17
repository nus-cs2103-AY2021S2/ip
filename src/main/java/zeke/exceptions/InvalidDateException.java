package zeke.exceptions;

public class InvalidDateException extends ZekeException {

    public InvalidDateException() {
    }

    @Override
    public String getMessage() {
        return "MonkaS! The date of the task is invalid. Type help for more info.\n";
    }
}

