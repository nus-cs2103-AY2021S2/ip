public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskInfo() {
        return "[T]" + super.getTaskInfo();
    }
}
