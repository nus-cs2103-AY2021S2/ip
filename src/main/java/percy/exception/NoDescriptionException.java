package percy.exception;

public class NoDescriptionException extends PercyException {
    String taskType;

    public NoDescriptionException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return " ☹ OOPS!!! The description of a " + taskType + " cannot be empty.";
    }
}
