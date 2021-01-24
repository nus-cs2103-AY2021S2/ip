package duke.Exceptions;

public class MissingTaskException extends DukeExceptions {

    public MissingTaskException () {
        super("OOPS!!! The task to be marked completed/ deleted cannot be empty.");
    }
}
