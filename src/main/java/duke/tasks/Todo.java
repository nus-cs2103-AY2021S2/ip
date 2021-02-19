package duke.tasks;

import duke.exceptions.DukeException;

/**
 * Todo class to handle todo entries which are the simplest type of task
 */
public class Todo extends Task {
    /**
     * Constructor for Todo with completion status specified
     *
     * @param description of the Todo entry
     * @param completed   <code>true</code> if the entry is completed, <code>false</code> otherwise
     */
    public Todo(String description, Boolean completed) {
        super(description, TaskType.TODO, completed);
    }

    /**
     * Constructor for Todo
     *
     * @param description of the Todo entry
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Parser for todo commands
     *
     * @param command to be parsed
     * @return Todo object initialized based on the command parsed
     * @throws DukeException if the command is an empty String
     */
    public static Todo parseTodo(String command) throws DukeException {
        if (command.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(command);
    }
}
