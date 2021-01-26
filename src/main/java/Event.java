public class Event extends Task {
    private final String time; //time and date of event

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String generateText() {
        return String.format("E # %d # %s # %s", this.isDone ? 1 : 0, this.description, this.time);
    }

    public String getTime() {
        return "(at: " + this.time + ")";
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[E][X] " + this.description + this.getTime();
        } else {
            return "[E][ ] " + this.description + this.getTime();
        }
    }
}
