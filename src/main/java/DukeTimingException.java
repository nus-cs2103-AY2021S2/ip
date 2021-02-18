public class DukeTimingException extends DukeException {
    public DukeTimingException(String type) {
        super("Given " + type + " has no timing!");
    }
}
