public class InvalidCommandException extends Exception {
    @Override
    public String getMessage() {
        return "Command is not recognized";
    }
}
