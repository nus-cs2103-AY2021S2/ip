package tasks;

public class TodoTask extends Task {
    private String type;

    public TodoTask(String taskName) {
        super(taskName);
        this.type = "[T]";
    }

    @Override
    public String toString() {
        return this.type + super.toString();
    }
}
