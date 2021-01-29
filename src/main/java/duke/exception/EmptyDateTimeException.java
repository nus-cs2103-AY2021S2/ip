package duke.exception;

/**
 * Exception where user left out the date or time of the task.
 */
public class EmptyDateTimeException {

    /**
     * Method to throw the Exception.
     */
    public EmptyDateTimeException() {
        System.out.println("------------------------------------------\n"
                + "☹ OOPS!!! Please key in a valid date and/or time in the format "
                + "dd/MM/yyyy HH:mm. \n"
                + "------------------------------------------");
    }
}
