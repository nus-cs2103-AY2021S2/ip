public class DukeDescriptionException extends DukeException {
    public DukeDescriptionException(String type) {
        super("The description of a " + type + " cannot be empty.");
    }
}
