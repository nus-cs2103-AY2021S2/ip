package zeke.exceptions;

public class InvalidDescriptionException extends ZekeException {

    public InvalidDescriptionException() {
    }

    @Override
    public String getMessage() {
        return "MonkaS! The description of the task is missing or invalid. Type help for more info\n";
    }
}
