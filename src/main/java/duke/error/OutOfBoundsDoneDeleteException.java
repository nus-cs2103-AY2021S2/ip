package duke.error;

public class OutOfBoundsDoneDeleteException extends IndexOutOfBoundsException {
    OutOfBoundsDoneDeleteException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "The task you entered does not exist!\n" + (char) 9 + (char) 9 + "Please re-enter!";
    }
}
