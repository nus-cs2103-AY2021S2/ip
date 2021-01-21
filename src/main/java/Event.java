public class Event extends Task{
    String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[E][X] " + this.name + " (at: " + this.time + ")";
        } else {
            return "[E][ ] " + this.name + " (at: " + this.time + ")";
        }
    }
}
