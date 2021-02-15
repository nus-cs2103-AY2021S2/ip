package antonio;

/**
 * Represents a Duke exception that is thrown during an error.
 */
public class AntonioException extends Exception {
    public AntonioException(String errorMessage) {
        super("Mamma Mia!!!\n" + errorMessage + "\nCapisci?");
    }
}
