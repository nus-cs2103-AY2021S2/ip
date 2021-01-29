package duke.exception;

/**
 * General exception for the Duke class.
 */
public class DukeException extends Exception {

    /**
     * Method to throw the Exception.
     */
    public DukeException() {
        System.out.println("------------------------------------------\n"
                + "☹ OOPS!!! Something went wrong.\n"
                + "------------------------------------------");
    }
}
