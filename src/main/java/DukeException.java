/**
 * A class that store all the possible display of errorsException in Duke.
 */
public class DukeException{

    /**
     * display the error message for empty body error.
     *
     */
    public static final void emptyBodyException() {
        System.out.println("Walao!Description empty!");
    }

    /**
     * display the error message for slash error.
     *
     */
    public static final void slashErrorException() {
        System.out.println("??? Too many / or no /, only 1 / allow for dates and time");
    }

    /**
     * display the error message for too many argument error.
     *
     */
    public static final void argumentErrorException() {
        System.out.println("You put so many/few ARGUMENTS for what");
    }

    /**
     * display the error message for missing argument error.
     *
     */
    public static final void missingArgumentErrorException() {
        System.out.println("Fill ur description or date lah");
    }

    /**
     * display the error message for command error.
     *
     */
    public static final void commandErrorException() {
        System.out.println("I DON'T KNOW WHAT U SAYING BRO");
    }

    /**
     * display the error message for missing task error.
     *
     */
    public static final void taskErrorException() {
        System.out.println("Walao, no such task");
    }
}
