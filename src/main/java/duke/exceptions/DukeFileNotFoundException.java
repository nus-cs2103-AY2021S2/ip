package duke.exceptions;

/**
 * DukeFileNotFoundException class is a class for all the exceptions thrown
 * when the file to load cannot be found
 * It inherit from the DukeException class
 */
public class DukeFileNotFoundException extends DukeException {
    
    /**
     * DukeFileNotFoundException class constructor
     */
    public DukeFileNotFoundException () {
        super("File not found!");
    }

}
