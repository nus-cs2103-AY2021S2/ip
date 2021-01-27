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

    public String getDueTime() {
        return dueTime;
    }

    @Override
    public String toSavedString() {
        return String.format(
                "E | %d | %s | %s",
                this.getIsDone() ? 1 : 0,
                this.getName(),
                this.getDueTime()
        );
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + (this.dueTime != null ? String.format(" (by: %s)", this.dueTime) : "");
    }
}
