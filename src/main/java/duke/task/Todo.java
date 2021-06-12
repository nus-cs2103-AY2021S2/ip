package duke.task;

import duke.exceptions.EmptyTaskDukeException;

/**
 * Todo task for tasks that have no fixed due date
 */
public class Todo extends Task {

    private final String TODO_IDENTIFIER = "[T]";
    private final String FILE_WRITER_IDENTIFIER = "T|";

    public Todo(String input) throws EmptyTaskDukeException {
        super(input);
    }

    public String getFileWriterIdentifier() {
        return FILE_WRITER_IDENTIFIER;
    }

    @Override
    public String toString() {
        String taskStringCheck = super.getIsTaskCompleted() ? super.COMPLETED_TEXT : " ";
        return TODO_IDENTIFIER + "[" + taskStringCheck + "] " + super.getTaskName();
    }
}
