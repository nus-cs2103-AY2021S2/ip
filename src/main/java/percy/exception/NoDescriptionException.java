package percy.exception;

public class NoDescriptionException extends PercyException {
    private String taskType;

    /**
     * Constructs NoDescriptionException.
     * @param taskType the type of task e.g. event, deadline, todo etc.
     */
    public NoDescriptionException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return " ☹ OOPS!!! The description of a " + taskType + " cannot be empty.";
    }
}
