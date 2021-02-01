package main.java;

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
