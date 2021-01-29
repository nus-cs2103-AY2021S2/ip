public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        this.taskType = "ToDo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
