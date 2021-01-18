public class Todo extends Task {

    public static Todo createTodo(String description) {
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