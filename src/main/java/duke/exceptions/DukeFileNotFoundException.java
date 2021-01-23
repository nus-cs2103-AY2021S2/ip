package duke.exceptions;

public class DukeFileNotFoundException extends DukeException{
    public DukeFileNotFoundException () {
        super("File not found!");
    }
}
