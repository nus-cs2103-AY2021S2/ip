public class Todo extends Task {

    public static Todo createTodo(String description) throws DukeException {
        if (description.length() == 0) {
                throw new DukeException("The description of Todo cannot be empty.");
        }
        return new Todo(description);
    }

    private Todo(String description) {
        super(description);
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}