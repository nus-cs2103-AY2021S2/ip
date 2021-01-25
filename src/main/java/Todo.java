public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskInfo() {
        return "[T]" + super.getTaskInfo();
    }
    @Override
    public String getTaskInfoOfFile() {
        return "T | " + (super.isDone ? "1" : "0") + " | " + super.getDescription();
    }
}
