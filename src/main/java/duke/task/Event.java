package duke.task;

public class Event extends Task {
    private final String at;

    public Event(String content, String at) {
        super(content);
        this.at = at;
    }

    private Event(String content, boolean isDone, String at) {
        super(content, isDone);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public static Event deserialize(String str) {
        String[] words = str.split(" \\| ");
        boolean isDone = Boolean.parseBoolean(words[1]);
        String content = words[2];
        String at = words[3];

        return new Event(content, isDone, at);
    }

    @Override
    public String getSerialized() {
        return String.format("E | %s | %s | %s", getIsDone(), getContent(), at);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), getContent(), at);
    }
}
