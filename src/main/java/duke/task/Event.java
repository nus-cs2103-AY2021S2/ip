package duke.task;

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
        boolean isDone = Boolean.parseBoolean(words[1]);
        String content = words[2];
        String at = words[3];

        Event event = new Event(content, at);
        if (isDone) {
            event.markDone();
        }

        return event;
    }

    /**
     * Returns true if the task contains str in one of its fields
     *
     * @param str the target string
     * @return true if the task contains str in one of its fields
     */
    @Override
    public boolean hasStrInProps(String str) {
        return getContent().contains(str) || at.contains(str);
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
