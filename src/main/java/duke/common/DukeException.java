package duke.common;

public class DukeException {
    public static class InvalidCommand extends IllegalArgumentException {
        public InvalidCommand() {
            super(DukeString.EXCEPTION_INVALID_COMMAND);
        }
    }

    public static class EmptyDescription extends IllegalArgumentException {
        public EmptyDescription(final String type) {
            super(String.format(DukeString.EXCEPTION_EMPTY_DESCRIPTION, type));
        }
    }

    public static class InvalidTask extends IllegalArgumentException {
        public InvalidTask() {
            super(DukeString.EXCEPTION_INVALID_TASK);
        }
    }

    public static class StorageError extends IllegalArgumentException {
        public StorageError() {
            super(DukeString.EXCEPTION_STORAGE_ERROR);
        }
    }
}
