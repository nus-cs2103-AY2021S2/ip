package todobeast.tasks;

import todobeast.commands.TaskType;

/**
 * A Task that represents a todo-type task. Todos do not have a date and time component to them.
 */
public class Todo extends Task {

    public Todo(String toDoDescription, boolean isDone, String taskNotes) {
        super(TaskType.TODO, toDoDescription, isDone, taskNotes);
    }

    /**
     * Formats this ToDo for storage using the specified delimiter
     * @param delimiter the delimiter used to join the various attributes within the task
     * @return the formatted string output of this task for storage.
     */
    public String formatForStorage(String delimiter) {
        String doneIndicator = isDone ? "1" : "0";
        String taskNotesForStorage = hasTaskNotes() ? delimiter + taskDescription : "";
        return taskType.toString() + delimiter
                + doneIndicator + delimiter
                + taskDescription + taskNotesForStorage;
    }

    @Override
    public String toString() {
        String doneIndicator = isDone ? "✔" : " ";
        String taskNotesForDisplay = hasTaskNotes() ? Task.TASK_DELIMITER + taskNotes : "";
        return taskType.toString() + Task.TASK_DELIMITER
                + doneIndicator + Task.TASK_DELIMITER
                + taskDescription + taskNotesForDisplay;
    }
}
