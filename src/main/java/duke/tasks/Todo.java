package duke.tasks;

public class Todo extends Task {
    public Todo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    public String toStorageString() {
        return "T || " + (this.isCompleted ? "1" : "0") + " || " + this.taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
