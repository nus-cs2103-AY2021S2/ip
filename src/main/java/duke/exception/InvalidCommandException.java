package duke.exception;

public class InvalidCommandException extends Exception {

    private String badCommand;

    public InvalidCommandException(String badCommand) {
        this.badCommand = badCommand;
	}

	private static final long serialVersionUID = 1L;
    
    @Override
    public String getMessage() {
        return "Command '" + badCommand + "' is not recognized.";
    }
}