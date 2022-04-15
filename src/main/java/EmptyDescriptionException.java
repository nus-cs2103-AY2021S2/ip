public class EmptyDescriptionException extends IndexOutOfBoundsException {
    public EmptyDescriptionException(String instruction) {
        super("☹ The description of a " + instruction + " cannot be empty.");
    }
}
