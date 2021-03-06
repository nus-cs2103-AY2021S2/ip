public class DukeTimingException extends DukeException {
    public DukeTimingException(String type) {
        super("Given " + type + " has no timing!");
    }

    public DukeTimingException() {
        super("Sorry, I don't understand the date you gave. ");
    }
}
