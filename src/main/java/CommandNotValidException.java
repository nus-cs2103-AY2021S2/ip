public class CommandNotValidException extends Exception {
    public CommandNotValidException() {
        super("Command not valid. Please use \"todo\", \"deadline\" or \"event\" before task description.");
    }
}
