package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;

/**
 * Todo class.
 *
 * A simple Task that does not require any other features other
 * than a description of the task.
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo.
     *
     * @param description Description of todo.
     */
    public Todo(String description) {
        this(description, false);
    }

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
     * No input validation is performed.
     *
     * @param s User input.
     * @return Todo
     * @throws DukeExceptionIllegalArgument When description is empty.
     */
    public static Todo parse(String s) throws DukeExceptionIllegalArgument {
        if (s.equals("")) {
            throw new DukeExceptionIllegalArgument("The description of a todo cannot be empty.");
        }
        return new Todo(s);
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

    /**
     * For minified printing into file.
     *
     * @return String representation of Todo.
     */
    public String toFileString() {
        return "T | " + ((isDone) ? 1 : 0) + " | " + description;
    }
}
