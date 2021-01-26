public class Event extends Task{
    protected String at;
    Event(String task, String at) {
        super(task);
        this.at = at;
    }

    Event(boolean done, String task, String at) {
        super(task);
        this.at = at;
    }

    String fileString() {
        return "E | " + this.done + " | " + this.task + " | " + this.at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + this.at + ")";
    }
}
