package duke.task;

public class TodoTask extends Task {
    public TodoTask(final String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
