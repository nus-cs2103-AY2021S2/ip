public class DukeException {
    /*
    //error for todo
    public static class TodoEmpty extends BaseException{
        public TodoEmpty(String message){
            super(message);
        }
    }
    //error for Invalid command
    public static class InvalidCommand extends BaseException{
        public InvalidCommand(String message){
            super(message);
        }
    }
     */

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
