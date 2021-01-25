package Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String serialise() {
        String type = "DEADLINE";
        StringBuilder sb = new StringBuilder();
        sb.append(type).append('|').append(isDone).append('|').append(description).append('|').append(by);

        return sb.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
