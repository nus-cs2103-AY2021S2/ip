public class ToDo extends Task {
    public ToDo(String taskInfo) {
        super(taskInfo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
