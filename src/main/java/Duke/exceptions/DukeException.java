package Duke.exceptions;

import Duke.ui.Ui;


/**
 * A class that store all the possible display of errorsException in Duke.
 */
public class DukeException{
    public static final void DATEFORMATEXCEPTION() {
        System.out.println(Ui.WRONGDATEFORMAT);
    }
    public static final void NumberFormatException() {
        System.out.println(Ui.KEYINNUMBER);
    }

    /**
     * display the error message for empty body error.
     *
     */
    public static final void emptyTaskException() {
        System.out.println(Ui.EMPTYTASK);
    }


    /**
     * display the error message for too many argument error.
     *
     */
    public static final void argumentErrorException() {
        System.out.println(Ui.TOOMANYARGUMENTS);
    }

    /**
     * display the error message for missing argument error.
     *
     */
    public static final void missingDateErrorException() {
        System.out.println(Ui.MISSINGDATE);
    }

    /**
     * display the error message for command error.
     *
     */
    public static final void commandErrorException() {
        System.out.println(Ui.COMMANDERROR);
    }

    /**
     * display the error message for missing task error.
     *
     */
    public static final void taskErrorException() { System.out.println(Ui.TASKERROR); }

    public static final void saveToFileError() { System.out.println(Ui.SAVETOFILEERROR);}
}
