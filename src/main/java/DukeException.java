public class DukeException {

    public static class TaskOutOfBoundsError extends BaseException {
        public TaskOutOfBoundsError(String message) {
            super(message);
        }
    }

    public static class EmptyTaskListError extends BaseException {
        public EmptyTaskListError(String message) {
            super(message);
        }
    }

    public static class TaskFormatError extends BaseException {
        public TaskFormatError(String message) {
            super(message);
        }
    }

    public static class NoMatchFound extends BaseException {
        public NoMatchFound(String message) {
            super(message);
        }
    }

    public static class FileLoadError extends BaseException {
        public FileLoadError(String message) {
            super(message);
        }
    }


}
