package duke.exceptions;

public class DukeException extends Exception{
    private  String message = "OOPS!";

    public DukeException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
