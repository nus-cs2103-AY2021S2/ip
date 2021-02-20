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
    public Todo(String description, TagList tags) {
        super(description, tags);
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
