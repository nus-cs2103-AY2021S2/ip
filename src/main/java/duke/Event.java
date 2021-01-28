package duke;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String[] eventArr) {
        super(eventArr[0]);
        this.at = eventArr[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String savedFormat() {
        String savedInfo;
        if (this.isDone()) {
            savedInfo = "E | 1 | " + this.getDescription(); 
        } else {
            savedInfo = "E | 0 | " + this.getDescription(); 
        }
        return savedInfo;
    }
}
