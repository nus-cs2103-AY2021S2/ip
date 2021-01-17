public class Event extends Task{
    protected String at;
    Event(String task, String at) {
        super(task);
        this.at = at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + this.at + ")";
    }
}
