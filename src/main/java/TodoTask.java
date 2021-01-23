public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description, 'T');
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
