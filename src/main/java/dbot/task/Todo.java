package dbot.task;


/**
 * An implementation of Task class that represents Todo Tasks.
 *
 * Todo tasks are tasks that only take a description and track whether they are done or not.
 *
 * The Todo class is visually represented with the prefix: [T]
 */
public class Todo extends Task {

    /**
     * Initialises a Todo task with the description String.
     *
     * @param description A String containing the Todo task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the exact String required to construct a replicate of this Todo Task.
     *
     * @return A String that can be used to construct a replicate Todo Task.
     */
    @Override
    public String getFullDescription() {
        return getDescription();
    }

    /**
     * Returns this Todo Task with its representative prefix: [T].
     *
     * @return A String representing this Todo Task with the prefix [T].
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
