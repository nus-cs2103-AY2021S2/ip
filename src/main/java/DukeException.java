/**
 * The DukeException is a child class of the Java Exception class
 * which throws an exception message when an invalid input is given to the chat bot
 * either a wrong message (unidentified input) or an unclear message (not specific enough)
 */
class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
