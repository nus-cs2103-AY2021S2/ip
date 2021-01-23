//public class DescriptionNotFoundException extends Exception {
public class DescriptionNotFoundException extends DukeException {
    public DescriptionNotFoundException() {
        super("\tPlease provide description for your task.\n");
    }
}
