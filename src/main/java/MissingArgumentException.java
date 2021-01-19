public class MissingArgumentException extends Exception {
    public MissingArgumentException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public MissingArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
