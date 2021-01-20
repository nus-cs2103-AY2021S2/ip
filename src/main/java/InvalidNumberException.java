public class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }

    public void printMessage() {
        System.out.println(super.getMessage());
    }
}
