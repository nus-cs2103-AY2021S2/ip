public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) throws DukeException {
        super(name);
        this.by = by;
    }

    public Deadline(String name, String by, boolean done) {
        super(name, done);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + by + ")";
    }
}
