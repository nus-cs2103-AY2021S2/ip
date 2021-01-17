public class Event extends Task {
    public String date;

    Event(String title) {
        super(title.substring(6).split(" /at ")[0]);
        this.date = title.substring(6).split(" /at ")[1];
        isDone = false;
    }
    @Override
    public String getDate() {
        return " (at: " + date + ")";
    }

    @Override
    public String toString() {
        String check = isDone ? "[X] " : "[ ] ";
        return "[E]" + check + title + getDate() + "\n";
    }
}
