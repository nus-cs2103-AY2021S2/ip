package owen;

public class OwenException extends Exception {
    public OwenException(String message) {
        super("Ooooo noo...\n" + message);
    }
}
