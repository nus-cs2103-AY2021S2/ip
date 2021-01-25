public class Event extends Task {
    private final String at;

    public Event(String content, String at) {
        super(content);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public static Event deserialize(String str) {
        String[] words = str.split(" \\| ");
        boolean isDone = words[1].equals("1");
        String content = words[2];
        String at = words[3];

        Event event = new Event(content, at);
        if (isDone) {
            event.markDone();
        }

        return event;
    }

    @Override
    public String getSerialized() {
        return String.format("E | %s | %s | %s", getSerializedIsDone(), getContent(), at);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), getContent(), at);
    }
}
