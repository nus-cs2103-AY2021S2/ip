public class TodoTask extends Task {
    public TodoTask(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
