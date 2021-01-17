public class UnknownInputException extends DukeException {
    public UnknownInputException(String type) {
        super("☹ OOPS!!! The description of a " + type + " cannot be empty.");
    }
}
