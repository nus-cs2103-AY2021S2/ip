package duke.exception;

/**
 * Customized exception class
 */
public class DukeException extends Exception {

    /** Create customized exception class objects
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Display exception message
     */
    public void printMessage() {
        System.out.println("----------------------------------------\n" + super.getMessage()
                + "\n----------------------------------------");
    }

}