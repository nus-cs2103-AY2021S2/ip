package todobeast.tasks;

/**
 * A Task that represents a todo-type task. Todos do not have a date and time component to them.
 */
public class Todo extends Task {
    public Todo(String toDoDescription) {
        super(toDoDescription);
    }

    public Todo(String toDoDescription, boolean isDone) {
        super(toDoDescription, isDone);
    }

    public String formatForStorage(String delimiter) {
        return "TODO" + delimiter + (isDone ? "1" : "0") + delimiter + taskDescription;
    }

    @Override
    public String toString() {
        return "TODO" + Task.TASK_DELIMITER + (isDone ? "done" : " ") + Task.TASK_DELIMITER + taskDescription;
    }
}
