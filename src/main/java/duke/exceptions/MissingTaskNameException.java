package duke.exceptions;

public class MissingTaskNameException extends DukeException {
    public MissingTaskNameException(String taskType) {
        super("Chotto matte I need to know the name of this " + taskType);
    }
}
