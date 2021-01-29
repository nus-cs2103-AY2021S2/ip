public class Todo extends Task {
    public Todo(String toDoDescription) {
        super(toDoDescription);
    }

    @Override
    public String toString() {
        return "TODO" + Task.TASK_DELIMITER + "[" + (done ? "X" : " ") + "]" + Task.TASK_DELIMITER + taskDescription;
    }
}
