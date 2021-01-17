public class Todo extends Task {
    Todo(String title) {
        super(title.substring(5));
    }

    @Override
    public String toString() {
        String check = isDone ? "[X] " : "[ ] ";
        return "[T]" + check + title + getDate() + "\n";
    }
}
