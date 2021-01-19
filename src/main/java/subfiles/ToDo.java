package main.java.subfiles;

/**
 * The ToDo class represents a single to-do item created by the
 * user via user input to the Duke program. It contains
 * functions which enable the user to mark the to-do as done.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + name;
    }

}
