package duke.task;

import duke.task.Task;

public class ToDo extends Task {

    /**
     * @param title the title of the todo task
     * @param isDone the status of the todo task
     */
    public ToDo(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * @param title the title of the task
     */
    public ToDo(String title) {
        this(title, false);
    }

    /**
     * @return a string describing the todo task
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
