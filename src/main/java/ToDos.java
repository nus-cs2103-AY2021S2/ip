/**
 * todos class
 */
public class ToDos extends Task {
    /**
     *
     * @param description the todo task description
     * @throws EmptyArgumentException if the description is empty
     */
    ToDos(String description) throws EmptyArgumentException {
        super(description);
    }

    /**
     *
     * @return string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
