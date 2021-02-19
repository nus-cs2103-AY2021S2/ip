package duke.task;

public class Event extends ListItem {
    private final String date;

    /**
     * Constructor for Event without task done status
     * @param task takes in string and pass to parent's constructor
     * @param inputDate takes in user's input date
     */
    public Event(String task, String inputDate) {
        super(task);
        this.date = inputDate;
    }

    /**
     * the overloaded constructor for Event
     * @param task takes in string and pass to parent's constructor
     * @param inputDate takes in user's input date
     * @param isDone the status of the task
     */
    public Event(String task, String inputDate, boolean isDone) {
        super(task, isDone);
        this.date = inputDate;
    }

    /**
     * creates a new Event item with a done status in immutable way
     * @return a ListItem with a runtime type Event
     */
    @Override
    public ListItem markAsDone() {
        return new Event(super.getTask(), this.date, true);
    }

    @Override
    public String toString() {
        return "[E]" + (super.isDone() == true ? "[X] " : "[ ] ")
                + super.getTask() + " (at: " + date + ")" + super.printTags();
    }

    public String getDate() {
        return "|" + date;
    }
}
