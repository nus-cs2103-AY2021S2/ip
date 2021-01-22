public class ToDoTask extends Task {
    public ToDoTask(String taskDescription, int isDone) {
        super(taskDescription, "T", isDone);
    }

    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
}
