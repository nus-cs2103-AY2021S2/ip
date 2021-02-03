package CustomExceptions;

public class InvalidTaskNumberException extends Exception {

    public InvalidTaskNumberException() {
        super("The input that follows a 'done' action should be an integer! :D");
    }
}
