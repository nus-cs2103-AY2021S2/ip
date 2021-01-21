public class Deadlines extends Task{
    public String by;
    //by is the time/date simple string
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + this.by + ")";
    }
}
