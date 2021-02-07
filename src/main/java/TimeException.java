public class TimeException extends DukeException {
    public TimeException(String event){
        super("The timing of " + event + "cannot be empty");
    }
}
