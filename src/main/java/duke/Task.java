package duke;

/**
 * Represents a Task to be recorded in the TaskList.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Returns a Task.
     *
     * @param description description of the task.
     * @param taskType the type of the task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns a copy of the Task
     *
     * @return A copy of the Task
     */
    public Task copy() {
        Task taskCopy = new Task(this.description, this.taskType);
        taskCopy.isDone = this.isDone;
        return taskCopy;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a Unicode representation of Boolean Values.
     *
     * @return Unicode Character.
     */
    public String getStatusIcon() {
        // return (isDone ? "✓" : "✘");
        // return (isDone ? "\u2713" : "\u2718");
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a String representing how it will be saved on the disk.
     *
     * @return String save representation of object.
     */
    public String saveTaskString() {
        String delimiter = " ~ ";
        return this.taskType.toString()
                + delimiter
                + ((this.isDone) ? 1 : 0)
                + delimiter
                + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.taskType.toString() + "][" + this.getStatusIcon() + "] " + this.description;
    }

}

enum TaskType {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    private String type;

    private TaskType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Generates a TaskType Object based on the Short-form String version.
     *
     * @param type String that represents the type in short-form
     * @return TaskType
     */
    public static TaskType fromString(String type) {
        for (TaskType t: TaskType.values()) {
            if (t.toString().equals(type)) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return this.type;
    }
}
