package duke.exception;

public class InvalidCommandException extends Exception {

    private String badCommand;

    /**
     * Create Exception to indicate an invalid command
     *
     * @param badCommand Invalid command fragment
     */
    public InvalidCommandException(String badCommand) {
        this.badCommand = badCommand;
	}

	private static final long serialVersionUID = 1L;
    
    @Override
    public String getMessage() {
        return "Command.Command '" + badCommand + "' is not recognized.";
    }
}