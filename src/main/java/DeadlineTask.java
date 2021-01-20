public class DeadlineTask extends Task {
    private String dueTime;

    public DeadlineTask(String name) {
        super(name);
    }

    public DeadlineTask(String name, String dueTime) {
        super(name);
        this.dueTime = dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + (this.dueTime != null ? String.format(" (by: %s)", this.dueTime) : "");
    }
}
