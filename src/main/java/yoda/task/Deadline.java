package yoda.task;

public class Deadline extends Task {
    public Deadline(String description, String by) {
        super(description);
        setDateTime(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "by " + formatDateTime();
    }
}
