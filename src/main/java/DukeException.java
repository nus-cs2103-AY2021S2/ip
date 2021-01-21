public class DukeException {
    public static class InvalidCommand extends Exception {
        public InvalidCommand() {
            super(DukeString.EXCEPTION_INVALID);
        }
    }

}
