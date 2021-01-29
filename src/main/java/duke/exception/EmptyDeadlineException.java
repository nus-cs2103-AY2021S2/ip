package duke.exception;

/**
 * Exception where user left out the details of the task.
 */
public class EmptyDeadlineException {

    /**
     * Method to throw the Exception.
     */
    public EmptyDeadlineException() {
        System.out.println("------------------------------------------\n"
                + "☹ OOPS!!! The description of a deadline cannot be empty.\n"
                + "------------------------------------------");
    }
}
