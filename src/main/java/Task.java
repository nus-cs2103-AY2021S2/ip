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

    @Override
    public String toString() {
        return "["+ this.taskType.toString() + "][" + this.getStatusIcon() + "] " + this.description;
    }

}

enum TaskType {
    TODO,
    EVENT,
    DEADLINE;

    @Override
    public String toString() {
        switch (this) {
            case TODO: return "T";
            case EVENT: return "E";
            case DEADLINE: return "D";
            default: throw new IllegalArgumentException();
        }
    }
}
