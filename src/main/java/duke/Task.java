package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

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
        return "["+ this.taskType.toString() + "][" + this.getStatusIcon() + "] " + this.description;
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
