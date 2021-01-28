package duke.task;

/**
 * Represents any todo task specified with event description and status.
 */
public class Todo extends Task {

    /**
     * Constructor for Event class.
     * @param description Description of the todo task.
     * @param status To check if the todo task is done.
     */
    public Todo(String description, int status) {
        super(description,status);
    }

    /**
     * Converts todo task details into txt format for local storage.
     * @return The text stored in the local file.
     */
    @Override
    public String toTxt() {
        return "T " + super.toTxt() + "\n";
    }

    /**
     * Generates todo task details displayed on the user interface.
     * @return Message output for Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
