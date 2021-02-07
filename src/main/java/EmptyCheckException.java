public class EmptyCheckException extends DukeException {
    private static final long serialVersionUID = 1L;

    public EmptyCheckException() {
        super("The date to check cannot be empty");
    }
}
