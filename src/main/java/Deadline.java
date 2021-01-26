/**
 * A Task with a deadline to complete.
 */
public class Deadline extends Task {
    protected String by;
    public Deadline(String description,String by) {
        super(description, "D");
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + this.getLetterCode() + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getSavedStringFormat() {
        return super.getSavedStringFormat() + " | " + by;
    }
}
