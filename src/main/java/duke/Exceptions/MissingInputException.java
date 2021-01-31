package duke.Exceptions;

public class MissingInputException extends DukeExceptions {

    public MissingInputException(String cat) {
        super("OOPS!!! The description of an " + cat + " cannot be empty.");
    }
}
