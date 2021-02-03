package CustomExceptions;

public class MissingEventTimeException extends Exception {

    public MissingEventTimeException() {
        super("You need to specify an event time following an '/at' marker...");
    }
}
