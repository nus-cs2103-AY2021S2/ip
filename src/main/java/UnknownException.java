public class UnknownException extends DukeException {
    public UnknownException(String type) {
        super("☹ OOPS!!! The description of a " + type + " cannot be empty.");
    }
}
