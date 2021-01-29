package duke.exceptions;

public class InvalidFolderException extends DukeException {

    public InvalidFolderException() {
        super("     ☹ OOPS!!! Error creating the file.\n"
                + "     Please create a data folder before trying again!");
    }
}
