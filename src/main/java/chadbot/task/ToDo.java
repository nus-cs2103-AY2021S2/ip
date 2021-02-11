package chadbot.task;

/**
 * The To-Do class represents a single to-do item created by the user via user input to the Duke program.
 * It contains functions which enable the user to mark the to-do as done.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class ToDo extends Task {

    /**
     * Default constructor for the To-Do class.
     *
     * @param name Description of the to-do.
     */
    public ToDo(String name) {
        super(name);

        assert(this.name != null);
    }

    /**
     * Returns a description of the to-do, formatted with its type,
     * followed by an "X" if it has been marked as done.
     *
     * @return Formatted description of the to-do.
     */
    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + name;
    }

}
