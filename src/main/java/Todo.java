public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskInfo() {
        return "[T]" + super.getTaskInfo();
    }

    @Override
    public String getPeriodDays() {
        return "this task hasn't determine the time";
    }
}
