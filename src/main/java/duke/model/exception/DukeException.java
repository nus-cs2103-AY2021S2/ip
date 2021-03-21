package duke.model.exception;

/**
 * Custom Exceptions for user to understand what went wrong combined with UIController and view
 */
public abstract class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public static class NoArgumentOrWrongFormatException extends DukeException {
        public NoArgumentOrWrongFormatException(String itemClass) {
            super("OOPS!!! The argument of the command " + itemClass + " cannot be empty or is in wrong format.");
        }
    }

    public static class UnknownCommandException extends DukeException {
        public UnknownCommandException() {
            super("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static class InputOutputErrorException extends DukeException {
        public InputOutputErrorException() {
            super("IO Error!");
        }
    }

    public static class IndexOutOfListSizeException extends DukeException {
        /**
         * throw when index is out of Duke's TaskList size
         */
        public IndexOutOfListSizeException() {
            super("OOPS!!! Input index is either smaller than 1 or out of the task list's size! "
                    + "Please try again with a different number within the range!");
        }
    }
}
