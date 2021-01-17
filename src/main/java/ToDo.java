public class ToDo extends Task {

    /**
     * Constructor of the ToDo class
     * @param description A brief description of the ToDo task
     */

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
