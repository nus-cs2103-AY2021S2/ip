package duke.exception;

/**
 * Exception where user inputs an invalid task detail.
 */
public class InvalidInstructionException {

    /**
     * Method to throw the Exception.
     */
    public InvalidInstructionException() {
        System.out.println("------------------------------------------\n"
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "------------------------------------------");
    }
}
