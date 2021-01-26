public class Deadlines extends Task {

    private String by;

    public Deadlines(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadlines(String name, String by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
