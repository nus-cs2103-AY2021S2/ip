public class OutOfBoundsDoneException extends IndexOutOfBoundsException {
    OutOfBoundsDoneException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "The task you entered does not exist! Please re-enter!";
    }
}
