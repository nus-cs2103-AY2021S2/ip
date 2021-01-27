public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, true);
        this.by = by;
    }


    @Override
    public Deadline finishTask() {
        return new Deadline(description, by, true);
    }

    @Override
    public String saveTask() {
        return String.format("D | %s | %s | %s\n", super.getStatusIcon(),
                description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}