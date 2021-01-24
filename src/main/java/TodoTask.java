public class TodoTask extends Task {

    public TodoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String getSavingString() {
        return "TODO" + super.getSavingString() + "\n";
    }
}
