public class Event extends Task{
    String time;
    
    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public Event(String name, boolean isDone, String time) {
        super(name, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }
}