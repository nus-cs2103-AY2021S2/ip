package duke.model.exception;

/**
 * Custom Exceptions for user to understand what went wrong combined with UIController and view
 */
public abstract class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public static class NoArgumentOrWrongFormatException extends DukeException {
        public static final String WRONG_FORMAT_MSG =
                "OOPS!!! The argument of the command %s cannot be empty or is in wrong format.";
        public NoArgumentOrWrongFormatException(String itemClass) {
            super(String.format(WRONG_FORMAT_MSG, itemClass));
        }
    }

    public static class UnknownCommandException extends DukeException {
        public static final String UNKNOWN_COMMAND_MSG = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        public UnknownCommandException() {
            super(UNKNOWN_COMMAND_MSG);
        }
    }

    public static class DeadlineEarlierThanNowException extends DukeException {
        public static final String TOO_EARLY_MSG = "OOPS!!! The date you've entered is earlier than today! Seems "
                + "like this is wrong!";
        public DeadlineEarlierThanNowException() {
            super(TOO_EARLY_MSG);
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
