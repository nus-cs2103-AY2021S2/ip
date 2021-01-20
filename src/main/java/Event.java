public class Event extends Task {
    private String at;

    public Event(String name, String at) throws DukeException {
        super(name);
        this.at = at;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: " + at + ")";
    }
}
