public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String saveToFile() {
        return "T" + super.toString();
    }

    @Override
    public String toString() {
        return "T" + super.toString();
    }
}
