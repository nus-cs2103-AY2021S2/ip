package duke.system.exception;

public abstract class DukeException extends Exception {

    private static final long serialVersionUID = 1L;

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public static class NoDescriptionException extends DukeException {
        public NoDescriptionException(String itemClass) {
            super("OOPS!!! The description of a " + itemClass + " cannot be empty.");
        }
    }

    public static class UnknownCommandException extends DukeException {
        public UnknownCommandException() {
            super("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static class IOErrorException extends DukeException {
        public IOErrorException() {
            super("IO Error!");
        }
    }
}