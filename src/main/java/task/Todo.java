package task;

import exception.DukeException;

/**
 * Class <code>Todo</code> extends <code>Task</code>.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo event with description specified by <code> input</code>.
     *
     * @param input task description.
     * @throws DukeException DukeException if description is empty.
     */
    public Todo(String input) throws DukeException {
        this.type = "T";
        if (input.length() != 0) {
            this.description = input;
        } else {
            throw new DukeException("☹ OOPS!!! Description of todo cannot be empty, please check!");
        }

    }

    /**
     * Constructs a Todo task with status as specified by <code>status</code> and the description
     * specified by <code>input</code>.
     *
     * @param status task status.
     * @param input  task description.
     * @throws DukeException DukeException if description is empty.
     */
    public Todo(String status, String input) throws DukeException {
        this.type = "T";
        this.isDone = status.equals("complete");
        if (input.length() != 0) {
            this.description = input;
        } else {
            throw new DukeException("☹ OOPS!!! Description of todo cannot be empty, please check!");
        }
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
