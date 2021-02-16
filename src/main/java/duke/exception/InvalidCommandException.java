package duke.exception;

public class InvalidCommandException extends Exception {
    private final String badCommand;

    /**
     * Create Exception to indicate an invalid command
     *
     * @param badCommand Invalid command fragment
     */
    public InvalidCommandException(String badCommand) {
        this.badCommand = badCommand;
    }
    @Override
    public String getMessage() {
        return "Command '" + badCommand + "' is not recognized.";
    }
}
