package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.UserInputTokenSet;

/**
 * Todo class.
 *
 * A simple Task that does not require any other features other
 * than a description of the task.
 */
public final class Todo extends Task {

    /**
     * Constructor for a Todo.
     *
     * @param description Description of todo.
     * @param isDone Whether task is completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns new Todo by parsing user string input.
     *
     * No input validation is performed. '/done' flag can be optionally provided
     * to mark as completed.
     *
     * @param tokenSet User input tokens
     * @return Todo
     * @throws DukeExceptionIllegalArgument When description is empty.
     */
    public static Todo parse(UserInputTokenSet tokenSet) throws DukeExceptionIllegalArgument {
        if (tokenSet.get("/text").isEmpty()) {
            throw new DukeExceptionIllegalArgument("The description of a todo cannot be empty.");
        }
        return new Todo(
                tokenSet.get("/text"),
                tokenSet.contains("done"));
    }

    /**
     * For pretty printing on stdout.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
