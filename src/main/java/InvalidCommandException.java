public class InvalidCommandException extends DukeException{
    public InvalidCommandException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
