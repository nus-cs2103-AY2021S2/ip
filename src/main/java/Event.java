public class Event extends Task {
    public String event;

    public Event(String str) {
        super(str);//super must be 1st line..
        String[] split = str.split("/");
        this.task = split[0].trim();
        this.event = split[1].trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +" "+ "(at: " + event + ")";
    }
}
