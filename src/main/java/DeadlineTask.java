public class DeadlineTask extends Task {
    String by;
    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.by + ")";
    }
}
