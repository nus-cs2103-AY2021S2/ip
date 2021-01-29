public class Todo extends Task {
    public Todo(String toDoDescription) {
        super(toDoDescription);
    }

    public Todo(String toDoDescription, boolean isDone) {
        super(toDoDescription, isDone);
    }

    @Override
    public String toString() {
        return "TODO" + Task.TASK_DELIMITER + (isDone ? "done" : " ") + Task.TASK_DELIMITER + taskDescription;
    }
}
