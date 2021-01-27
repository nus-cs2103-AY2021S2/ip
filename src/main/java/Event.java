public class Event extends Task {
    private String at;

    public Event(String name, String at) throws DukeException {
        super(name);
        this.at = at;
    }

    public Event(String name, String at, boolean done) {
        super(name, done);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: " + at + ")";
    }
}
