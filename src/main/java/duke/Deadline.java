package duke;

public class Deadline extends Task {

    public String date;

    Deadline(String description, String date) {
        super(description);
        this.date = date;
        isDone = false;
    }

    @Override
    public String getDate() {
        return " (by: " + date + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getDate() + "\n";
    }
}
