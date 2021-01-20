class Deadline extends Task {
    protected final String deadline;

    public Deadline(String deadlineInfo) {
        super((deadlineInfo.split("/by")[0]).substring(0,
                deadlineInfo.split("/by")[0].length() - 1));
        this.deadline = deadlineInfo.split("/by")[1].substring(1);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[D][X] " + this.taskName + " (by: " + deadline + ")";
        } else {
            return "[D][ ] " + this.taskName + " (by: " + deadline + ")";
        }
    }
}