package duke.exception;

/**
 * Exception where user left out the details of the task.
 */
public class EmptyToDoException {

    /**
     * Method to throw the Exception.
     */
    public EmptyToDoException() {
        System.out.println("------------------------------------------\n"
                + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                + "------------------------------------------");
    }
}
