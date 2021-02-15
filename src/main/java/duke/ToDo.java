package duke;

/**
 * This class represents a Todo task.
 */
class ToDo extends Task {

    /**
     * Creates a new Todo task object.
     *
     * @param taskName The name of the Todo task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Overloaded constructor to create a Todo task object. It accepts one extra
     * argument to determine if the task is already done.
     *
     * @param taskName The name of the task.
     * @param isDone Whether the task is already done.
     */
    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Prints the details of the task in a specific format.
     *
     * @return A string representation of the details of the Todo task.
     */
    @Override
    public String toString() {
        assert (this.taskName.equals("")) : "Name of todo cannot be empty";
        String typeOfTask = "[T]";
        String isCompleted;
        if (this.isDone) {
            isCompleted = "[X] ";
        } else {
            isCompleted = "[ ] ";
        }
        String message = typeOfTask + isCompleted + this.taskName;
        return message;
    }
}
