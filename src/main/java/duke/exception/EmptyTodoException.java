package duke.exception;

/**
 * Exception where user left out the details of the task.
 */
public class EmptyTodoException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public EmptyTodoException() {
        super("The description of a todo cannot be empty.\n"
                + "Try 'todo read a book'");
    }
}
