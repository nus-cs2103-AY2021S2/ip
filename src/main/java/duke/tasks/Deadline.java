package duke.tasks;

public class Deadline extends Task{
    private static final String TYPE = "DEADLINE";
    protected String by;

    public Deadline(String description, String by) {
        super(description, TYPE);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
 }
