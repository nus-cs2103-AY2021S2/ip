public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws EmptyArgument {
        super(description);
        by = by.trim();
        if (by.isEmpty()){
            throw new EmptyArgument();
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (Deadline: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D," + super.toBaseFileString() + "," + by.length() + "," + by;
    }
}