package duke.exceptions;

import duke.ui.Ui;


/**
 * A class that store all the possible display of errorsException in Duke.
 */
public class DukeException extends Exception{
    public DukeException(String errorMessage) {
        super(errorMessage);
    }


    public static final void NumberFormatException() {
        System.out.println(Ui.KEYINNUMBER);
    }

    public static final void taskErrorException() { System.out.println(Ui.TASKERROR); }

    public static final void saveToFileError() { System.out.println(Ui.SAVETOFILEERROR);}
}
