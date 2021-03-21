package antonio;

/**
 * Represents a Antonio exception that is thrown during an error.
 */
public class AntonioException extends Exception {
    public AntonioException(String errorMessage) {
        super("Mamma Mia!!!\n" + errorMessage + "\nCapisci?");
    }
}
