package duke;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String[] deadlineArr) {
        super(deadlineArr[0]);
        this.by = deadlineArr[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String savedFormat() {
        String savedInfo;
        if (this.isDone()) {
            savedInfo = "D | 1 | " + this.getDescription(); 
        } else {
            savedInfo = "D | 0 | " + this.getDescription(); 
        }
        return savedInfo;
    }
}