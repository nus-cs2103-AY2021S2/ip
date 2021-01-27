import java.time.LocalDate;

class Deadline extends Task {
    protected final LocalDate date;

    public Deadline(String deadlineInfo) throws DukeException {
        super((deadlineInfo.split("/by")[0]).substring(0,
                deadlineInfo.split("/by")[0].length() - 1));
        try {
            this.date = LocalDate.parse(deadlineInfo.split("/by")[1].substring(1));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid input for new deadline");
        }
    }

    public String getDate() {
        return Task.printDate(this.date);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[D][X] " + this.taskName + " (by: " + Task.printDate(date) + ")";
        } else {
            return "[D][ ] " + this.taskName + " (by: " + Task.printDate(date) + ")";
        }
    }
}