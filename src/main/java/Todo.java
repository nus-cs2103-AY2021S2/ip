public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSaveTime() {
        return "";
    }

    @Override
    public String getSaveType() {
        return "T";
    }

    static Todo getTodo(String description) {
        return new Todo(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
