package duke.tasks;

/**
 * TodoTask class models an actual task that the user is about to do or has completed
 * It also inherits from the Task class.
 * Its details include the description
 */
public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description, "[T]");
    }

    /**
     * Constructor to initialize the ToDoTask
     *
     * @param description the description of the EventTask
     */
    public ToDoTask(String description, String priority) {
        super(description, "[T]", priority);
    }

    /**
     * toString method of ToDoTask which prints out details of the ToDoTask
     */
    @Override
    public String toString() {
        return "       " + this.type + super.toString().trim() + "\n       Priority: " + this.priority.getValue();
    }
}
