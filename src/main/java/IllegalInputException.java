public class IllegalInputException extends IllegalArgumentException {
    IllegalInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "I don't understand what you just entered >.<\n" + (char) 9 + (char) 9 + "Please re-enter!";
    }
}
