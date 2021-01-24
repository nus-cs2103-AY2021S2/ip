public class DeadlineTask extends Task {
    String by;
    public DeadlineTask(String description, boolean isDone, String by) {
        super(description);
        super.isDone = isDone;
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.by + ")";
    }
    @Override
    public String getTaskDetails() {
        String divider = " | ";
        return "D" +  divider
                + (isDone ? "1" : "0") + divider
                + description + divider
                + by;
    }
}
