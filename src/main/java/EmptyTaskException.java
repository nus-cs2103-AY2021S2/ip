public class EmptyTaskException extends DukeException {
    private static final long serialVersionUID = 7128896708432192178L;

    public EmptyTaskException(String taskType) {
        super("The description of a " + taskType + " cannot be empty.");
    }
}
