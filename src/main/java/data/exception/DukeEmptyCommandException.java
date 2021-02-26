package data.exception;

public class DukeEmptyCommandException extends IllegalArgumentException{
    public DukeEmptyCommandException(String s){
        super(s);
    }
}
