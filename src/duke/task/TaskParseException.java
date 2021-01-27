package duke.task;

public class TaskParseException extends Exception {
    public TaskParseException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
