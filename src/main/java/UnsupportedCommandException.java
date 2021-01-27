public class UnsupportedCommandException extends Exception {
    public UnsupportedCommandException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public UnsupportedCommandException(String errorMessage) {
        super(errorMessage);
    }

    public UnsupportedCommandException() {
        super("I don't recognise this command. Please try something else.");
    }
}


