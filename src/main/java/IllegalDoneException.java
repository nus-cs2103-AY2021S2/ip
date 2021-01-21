public class IllegalDoneException extends StringIndexOutOfBoundsException {
    IllegalDoneException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "You forgot to enter the task to delete! Please re-enter!";
    }
}
