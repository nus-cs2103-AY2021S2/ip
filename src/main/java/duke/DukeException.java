package duke;
public class DukeException extends IllegalArgumentException {
    public DukeException(String message){
        super(message);
    }
    @Override
    public String toString(){
        return this.getMessage();
    }
}
