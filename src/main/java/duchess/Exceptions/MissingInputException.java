package duchess.Exceptions;

public class MissingInputException extends duchess.Exceptions.DuchessExceptions {

    public MissingInputException(String cat) {
        super("OOPS!!! The description of a " + cat + " cannot be empty.");
    }
}
