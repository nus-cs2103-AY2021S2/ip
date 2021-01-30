package duke;

public class EmptyTaskDukeException extends Exception {
    public EmptyTaskDukeException() {
        super("Description of a task cannot be empty!\n"
                + "Please enter a valid input.");
    }
}
