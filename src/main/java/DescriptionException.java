public class DescriptionException extends DukeException {
    public DescriptionException(String event){
        super("The description of a " + event + " cannot be empty");
    }
}
