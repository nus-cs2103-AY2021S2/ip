package todobeast.tasks;

import todobeast.tasks.Task;

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
