package duke.task;

public class Event extends Task{
    protected String at;

    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    public Event(boolean done, String task, String at) {
        super(task);
        this.at = at;
        this.done = done;
    }

    public String fileString() {
        return "E | " + this.done + " | " + this.task + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + this.at + ")";
    }
}
