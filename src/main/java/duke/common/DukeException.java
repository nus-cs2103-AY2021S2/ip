package duke.common;

/**
 * A utility class to consolidate all exceptions related to Duke.
 */
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

    public static class InvalidDateFormat extends IllegalArgumentException {
        public InvalidDateFormat() {
            super(DukeString.EXCEPTION_INVALID_DATE);
        }
    }

    public static class EmptyDeadlineDate extends IllegalArgumentException {
        public EmptyDeadlineDate() {
            super(DukeString.EXCEPTION_EMPTY_DEADLINE_DATE);
        }
    }

    public static class EmptyEventDate extends IllegalArgumentException {
        public EmptyEventDate() {
            super(DukeString.EXCEPTION_EMPTY_EVENT_DATE);
        }
    }

    public static class InvalidEventEnd extends IllegalArgumentException {
        public InvalidEventEnd() {
            super(DukeString.EXCEPTION_INVALID_EVENT_END);
        }
    }

    public static class InvalidDateForTask extends UnsupportedOperationException {
        public InvalidDateForTask(String task) {
            super(String.format(DukeString.EXCEPTION_INVALID_DATE_FOR_TASK, task));
        }
    }

    public static class StorageReadError extends IllegalArgumentException {
        public StorageReadError() {
            super(DukeString.EXCEPTION_STORAGE_READ_ERROR);
        }
    }

    public static class StorageWriteError extends IllegalArgumentException {
        public StorageWriteError() {
            super(DukeString.EXCEPTION_STORAGE_WRITE_ERROR);
        }
    }
}
