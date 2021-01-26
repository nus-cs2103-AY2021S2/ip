public class Deadline extends Task {
    private final String dateBy; //deadline of task

    public Deadline(String description, String dateBy) {
        super(description);
        this.dateBy = dateBy;
    }

    @Override
    public String generateText() {
        return String.format("D # %d # %s # %s", this.isDone ? 1 : 0, this.description, this.dateBy);
    }

    public String getDeadline() { //get deadline in format of String eg. (by: Sunday)
        return "(by: " + dateBy + ")";
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[D][X] " + this.description + this.getDeadline();
        } else {
            return "[D][ ] " + this.description + this.getDeadline();
        }
    }
}
