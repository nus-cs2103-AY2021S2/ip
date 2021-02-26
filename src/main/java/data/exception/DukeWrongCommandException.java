package data.exception;

public class DukeWrongCommandException extends IllegalArgumentException{
    public DukeWrongCommandException (String s){
        super(s);
    }
}
