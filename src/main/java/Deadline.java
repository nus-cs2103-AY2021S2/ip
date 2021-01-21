public class Deadline extends Task {

    private String deadline;

    public Deadline(String details, String deadline) {
        super(details);
        this.deadline = deadline;
    }

    private Deadline(String details, String deadline, boolean indicator) {
        super(details, indicator);
        this.deadline = deadline;
    }

    // overrides completeTask() method
    public Deadline completeTask() {
        return new Deadline(this.getTask_details(), deadline,true);
    }

    // overrides taskStatus() method
    public String taskStatus() {
        if (this.isDone()) {
            return "[D][X] " + this.getTask_details() + " (by: " + deadline + ")";
        } else {
            return "[D][ ] " + this.getTask_details() + " (by: " + deadline + ")";
        }
    }
}
