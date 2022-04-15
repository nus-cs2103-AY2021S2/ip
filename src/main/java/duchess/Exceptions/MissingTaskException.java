package duchess.Exceptions;

public class MissingTaskException extends DuchessExceptions {

    public MissingTaskException () {
        super("OOPS!!! The task to be marked completed/ deleted cannot be empty.");
    }
}
