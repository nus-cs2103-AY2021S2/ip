package main.java.command;

import main.java.classes.Task;

/**
 * ToDo inherits from Task.
 * ToDo is specified by [T].
 */
public class ToDo extends Task {
    public ToDo(String description) {

        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
