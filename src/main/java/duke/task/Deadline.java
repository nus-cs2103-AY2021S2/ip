package duke.task;

public class Deadline extends DatedTask {

    public Deadline(String task, String date) throws TaskException {
        super(task, date);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + formatDate(this.date) + ")";
    }
}
