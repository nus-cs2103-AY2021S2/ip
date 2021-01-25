public class Event extends Task {
    private final String at;

    public Event(String content, String at) {
        super(content);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), getContent(), at);
    }
}
