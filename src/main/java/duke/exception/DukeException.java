package duke.exception;

/**
 * Customized exception class to display customized exception
 */
public class DukeException extends Exception {

    /** Create customized exception
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