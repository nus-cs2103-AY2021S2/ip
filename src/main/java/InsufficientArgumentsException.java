/**
 * InsufficientArgumentsException is raised when the user gives less command than required
 */
public class InsufficientArgumentsException extends Exception {
    public InsufficientArgumentsException(String e) {
        super(e);
    }
}
