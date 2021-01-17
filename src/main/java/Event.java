public class Event extends Task {
    public String date;

    Event(String description) {
        super(description.substring(6).split(" /at ")[0]);
        this.date = description.substring(6).split(" /at ")[1];
        isDone = false;
    }
    @Override
    public String getDate() {
        return " (at: " + date + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getDate() + "\n";
    }
}
