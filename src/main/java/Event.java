public class Event extends Task{
    protected String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
        this.cat = 'E';
    }
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + time + ")" ;
    }
}
