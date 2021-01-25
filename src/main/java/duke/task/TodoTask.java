package duke.task;

/**
 * The TodoTask class encapsulates information and methods about a Todo Task
 * and inherits functionality from the Task class.
 */
public class TodoTask extends Task {

    public TodoTask(String taskName) {
        super(taskName);
    }

    /**
     * Returns a string representation of a Todo task for the user.
     * @return Type of task, status, followed by the name of Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation in the format of how the Todo task should be saved in the data file.
     * @return String for chatbot to save into a saved data file of tasks.
     */
    public String getSavingString() {
        return "TODO" + super.getSavingString() + "\n";
    }
}
