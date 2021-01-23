package surrealchat.task;

public class ToDoTask extends Task {
    public ToDoTask(String taskDescription, boolean isDone) {
        super(taskDescription, "T", isDone);
    }

    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
}
