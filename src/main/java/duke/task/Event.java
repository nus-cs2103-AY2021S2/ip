package duke.task;

public class Event extends DatedTask {

    public Event(String task, String date) throws TaskException {
        super(task, date);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + formatDate(this.date) + ")";
    }
}


