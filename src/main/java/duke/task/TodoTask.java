package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * The TodoTask class encapsulates information and methods about a Todo Task
 * and inherits functionality from the Task class.
 */
public class TodoTask extends Task {

    private static final String SEPARATOR = "|";

    public TodoTask(String taskName) {
        super(taskName);
    }

    /**
     * Returns a string representation of a Todo task for the user.
     * @return Type of task, status, followed by the name of Todo task.
     */
    @Override
    public String toString() {
        String doneDateString = isDone
                ? " (completed: " + doneDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh.mm a")) + ")"
                : "";
        return "[T]" + super.toString() + doneDateString;
    }

    /**
     * Returns a string representation in the format of how the Todo task should be saved in the data file.
     * @return String for chatbot to save into a saved data file of tasks.
     */
    public String getSavingString() {
        if (isDone) {
            return "TODO" + super.getSavingString()
                    + SEPARATOR + doneDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "\n";
        }
        return "TODO" + super.getSavingString() + "\n";
    }
}
