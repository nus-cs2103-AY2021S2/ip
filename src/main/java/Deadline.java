public class Deadline extends Task{
    /**
     * Returns a Deadline
     * @param description description of the deadline
     * @param deadline , which is currently still in String form but I suspect that might change
     * **/
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String type = "[D]";
        String doneStatus = "[" + getStatusIcon() + "]";
        return type + doneStatus + " " + this.description + "(" + this.deadline + ")";
    }

    String deadline;
}
