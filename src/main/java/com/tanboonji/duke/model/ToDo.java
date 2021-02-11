package com.tanboonji.duke.model;

/**
 * The ToDo class stores information about a todo task.
 */
public class ToDo extends Task {

    /**
     * Class constructor specifying the description of task.
     *
     * @param description Task description.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
