package duke.exceptions;

import duke.ui.Ui;


/**
 * A class that store all the possible display of errorsException in Duke.
 */
public class DukeException extends Exception{
    public DukeException(String errorMessage) {
        super(errorMessage);
    }


    public static void NumberFormatException() {
        System.out.println(Ui.KEY_IN_NUMBER);
    }

    public static void taskErrorException() { System.out.println(Ui.TASK_ERROR); }

    public static void saveToFileError() { System.out.println(Ui.SAVE_TO_FILE_ERROR);}
}
