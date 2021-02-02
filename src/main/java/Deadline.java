public class Deadline extends Task {
    /**
     * Returns a Deadline
     *
     * @param description description of the deadline
     * @param deadline    , which is currently still in String form but I suspect that might change
     **/
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(Boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String type = "[D]";
        String doneStatus = "[" + getStatusIcon() + "]";
        return type + doneStatus + " " + this.description + "(" + this.deadline + ")";
    }

    String deadline;

    /***
     * Format = {type}{done}{description}{deadline}
     */
    public String toStorage() {
        //type
        String res = "D";
        //done status
        res += "\u001E" + (isDone ? "T" : "F");
        //description
        res += "\u001E" + this.description;
        //deadline
        res += "\u001E" + this.deadline;
        return res;
    }
}
