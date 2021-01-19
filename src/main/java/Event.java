public class Event extends Task {
    private final String time; //time and date of event

    public Event(String description, int id, String time) {
        super(description, id);
        this.time = time;
    }

    public String getTime() {
        return "(at: " + this.time + ")";
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[E][X] " + this.description;
        } else {
            return "[E][ ] " + this.description;
        }
    }
}
