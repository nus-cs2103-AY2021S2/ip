package com.nus.duke.data;

/**
 * Todo is a type of Task that contains just a description.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
