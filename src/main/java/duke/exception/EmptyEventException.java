package duke.exception;

/**
 * Exception where user left out the details of the task.
 */
public class EmptyEventException {

    /**
     * Method to throw the Exception.
     */
    public EmptyEventException() {
        System.out.println("------------------------------------------\n"
                + "☹ OOPS!!! The description of an event cannot be empty.\n"
                + "------------------------------------------");
    }
}
