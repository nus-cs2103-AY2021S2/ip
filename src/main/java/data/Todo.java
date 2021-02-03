package data;

public class Todo extends Task {

    private Todo() {
        super();
    }

    /**
     * Todo constructor
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the Todo with status icon
     *
     * @return Todo string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
