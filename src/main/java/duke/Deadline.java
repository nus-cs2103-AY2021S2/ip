package duke;

public class Deadline extends Task {

    public String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        isDone = false;
    }

    @Override
    public String getDate() {
        return " (by: " + date + ")";
    }

    public String getFormattedString() {
        return "DEADLINE::" + (isDone? "1::" : "0::") + description + "::" + date + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getDate() + "\n";
    }
}
