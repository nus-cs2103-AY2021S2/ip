public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + by + ")";
    }
}
