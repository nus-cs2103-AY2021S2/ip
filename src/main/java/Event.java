public class Event extends Task{

    protected String at;

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public static Event getEvent(String description, String by) {
        return new Event(description, by, false);
    }

    @Override
    public Task markDone(){
        return new Event(description,at,true);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
