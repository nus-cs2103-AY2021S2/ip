public class UnsupportedCommandException extends Exception {
    public UnsupportedCommandException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    // todo - can print out the command that was inputted wrongly
    public UnsupportedCommandException(String errorMessage) {
        super(errorMessage);
    }

    public UnsupportedCommandException() {
        super("I don't recognise this command. Please try something else.");
    }
}


