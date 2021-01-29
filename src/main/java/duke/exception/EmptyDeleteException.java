package duke.exception;

/**
 * Exception where user left out the details of the task to delete.
 */
public class EmptyDeleteException {

    /**
     * Method to throw the Exception.
     */
    public EmptyDeleteException() {
        System.out.println("------------------------------------------\n"
                + "☹ OOPS!!! The description of a delete cannot be empty.\n"
                + "------------------------------------------");
    }
}
