package duke.exception;

public class InvalidCommandException extends Exception {
    private static final long serialVersionUID = 1L;
    private String badCommand;

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
