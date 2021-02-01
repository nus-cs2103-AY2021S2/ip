/**
 * WrongArgumentsException is raised when the user gives invalid commands
 */

public class WrongArgumentException extends Exception {
    public WrongArgumentException(String e) {
        super(e);
    }
}
