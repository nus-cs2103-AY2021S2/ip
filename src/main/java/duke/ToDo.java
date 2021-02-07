package duke;

/**
 * This class represents a todo task.
 */
class ToDo extends Task {

    /**
     * Creates a new todo task object.
     *
     * @param taskName The name of the todo task
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Overloaded constructor to create a todo task object. It accepts one extra argument
     * to determine if the task is already done.
     *
     * @param taskName The name of the task.
     * @param isDone Whether the task is already done.
     */
    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Prints the details of the task in a special format.
     *
     * @return the details of the task, such as the type, whether it is done and its date.
     */
    @Override
    public String toString() {
        String type = "[T]";
        String isCompleted;
        if (this.isDone) {
            isCompleted = "[X]";
        } else {
            isCompleted = "[ ]";
        }
        assert (this.taskName.equals("")) : "Name of todo cannot be empty";
        return type + isCompleted + " " + this.taskName;
    }
}
