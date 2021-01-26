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

    public String getStatusIcon() {
        // return (isDone ? "✓" : "✘");
        // return (isDone ? "\u2713" : "\u2718");
        return (isDone ? "X" : " ");
    }
    
    public String getDescription() {
        return this.description;
    }

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
