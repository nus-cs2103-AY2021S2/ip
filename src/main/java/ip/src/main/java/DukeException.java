package ip.src.main.java;

/**
 * Customized Exception Class for Duke.
 */

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
