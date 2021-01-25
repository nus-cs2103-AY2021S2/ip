package tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.taskType = "Todo";
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
        this.taskType = "Todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
