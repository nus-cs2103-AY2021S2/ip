package command;

import classes.Task;

/**
 * ToDo inherits from Task.
 * ToDo is specified by [T].
 */
public class ToDo extends Task {
    /**
     * Constructor method.
     * @param description User input description of task.
     */
    public ToDo(String description) {

        super(description);
    }

    /**
     * Method to return a formatted version of user's input.
     * @return Formatted String of user's input.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
