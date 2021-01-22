/**
 * A class that store all the possible display of errorsException in Duke.
 */
public class DukeException{

    public static final void NumberFormatException() {
        System.out.println(Format.KEYINNUMBER);
    }

    /**
     * display the error message for empty body error.
     *
     */
    public static final void emptyTaskException() {
        System.out.println(Format.EMPTYTASK);
    }


    /**
     * display the error message for too many argument error.
     *
     */
    public static final void argumentErrorException() {
        System.out.println(Format.TOOMANYARGUMENTS);
    }

    /**
     * display the error message for missing argument error.
     *
     */
    public static final void missingDateErrorException() {
        System.out.println(Format.MISSINGDATE);
    }

    /**
     * display the error message for command error.
     *
     */
    public static final void commandErrorException() {
        System.out.println(Format.COMMANDERROR);
    }

    /**
     * display the error message for missing task error.
     *
     */
    public static final void taskErrorException() { System.out.println(Format.TASKERROR); }
}
