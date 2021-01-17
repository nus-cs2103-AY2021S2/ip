public class Todo extends Task {
    Todo(String description) {
        super(description.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + getDate() + "\n";
    }
}
