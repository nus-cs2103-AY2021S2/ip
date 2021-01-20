class Deadline extends Task {
    protected final String deadline;

    public Deadline(String deadlineInfo) throws DukeException{
        super((deadlineInfo.split("/by")[0]).substring(0,
                deadlineInfo.split("/by")[0].length() - 1));
        try {
            this.deadline = deadlineInfo.split("/by")[1].substring(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid input for new deadline");
        }
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