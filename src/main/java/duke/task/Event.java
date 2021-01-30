package duke.task;

public class Event extends ListItem {
    private final String date;

    public Event(String task, String inputDate) {
        super(task);
        this.date = inputDate;
    }

    public Event(String task, String inputDate, boolean isDone) {
        super(task, isDone);
        this.date = inputDate;
    }

    @Override
    public ListItem markAsDone() {
        return new Event(super.getTask(), this.date, true);
    }

    @Override
    public String toString() {
        return "[E]" + (super.getDone() == true ? "[X] " : "[ ] ") + super.getTask() + " (at: " + date + ")";
    }

    public String getDate() {
        return "|" + date;
    }
}
