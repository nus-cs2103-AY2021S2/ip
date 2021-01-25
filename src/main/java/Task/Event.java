package Task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String serialise() {
        String type = "EVENT";
        StringBuilder sb = new StringBuilder();
        sb.append(type).append('|').append(isDone).append('|').append(description).append('|').append(at);

        return sb.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
