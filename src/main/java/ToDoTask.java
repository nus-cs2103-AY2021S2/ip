public class ToDoTask extends Task {
    public ToDoTask(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
