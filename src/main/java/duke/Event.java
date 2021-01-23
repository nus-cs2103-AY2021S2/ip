package duke;

public class Event extends Task {
    public String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
        isDone = false;
    }
    @Override
    public String getDate() {
        return " (at: " + date + ")";
    }

    public String getFormattedString() {
        return "EVENT::" + (isDone? "1::" : "0::") + description + "::" + date + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getDate() + "\n";
    }
}
