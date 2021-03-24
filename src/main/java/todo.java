/**
 * Type of task that the user can input.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo objects.
     * @param description Describes what is to be done.
     */
    Todo(String description) {
        super(description, null, null);
    }

    /**
     * Getter which returns an identifier for the type of task it is.
     * @return T for Todo.
     */
    @Override
    public String getInitial() {
        return "T";
    }
}
