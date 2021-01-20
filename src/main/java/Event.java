public class Event extends Task {
    protected String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    static public Event parse(String input) throws DukeException {
        String body = input.split("event")[1].strip();
        if(!body.trim().isEmpty()) {
            String[] parts = body.split("/at");
            String desc = parts[0].strip();
            String at = parts[1].strip();
            return new Event(desc, at);
        } else {
            throw new DukeException("Event description cannot be empty");
        }
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + desc + " (at: " + at + ")";
    }
}
