public class MissingInputException extends DukeExceptions {

    public MissingInputException(String cat) {
        super("☹ OOPS!!! The description of a " + cat +  " cannot be empty.");
    }
}
