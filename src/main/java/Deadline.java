class Deadline extends Task {
    protected final String date;

    public Deadline(String deadlineInfo) throws DukeException {
        super((deadlineInfo.split("/by")[0]).substring(0,
                deadlineInfo.split("/by")[0].length() - 1));
        try {
            this.date = deadlineInfo.split("/by")[1].substring(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid input for new deadline");
        }
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[D][X] " + this.taskName + " (by: " + date + ")";
        } else {
            return "[D][ ] " + this.taskName + " (by: " + date + ")";
        }
    }
}