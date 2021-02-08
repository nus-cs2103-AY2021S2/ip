public class DeadlineException extends DukeException {
    private static final long serialVersionUID = 5843754080272552309L;

    public DeadlineException(String s) {
        super(s + "\nPlease re-enter the command in the format:\ndeadline <task> /by <YYYY-MM-DD>");
    }
}