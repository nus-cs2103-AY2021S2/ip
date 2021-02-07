package duke.task;

import java.util.Objects;

/**
 * Represents a task that happens at a specific location and/or date and time.
 */
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

    /**
     * Deserialize the given string to create an Event instance. The string should be generated by #getSerialized().
     *
     * @param str the string to deserialize from
     * @return the created Event instance
     * @see #getSerialized()
     */
    public static Event deserialize(String str) {
        String[] words = str.split(" \\| ");
        boolean isDone = Boolean.parseBoolean(words[1]);
        String content = words[2];
        String at = words[3];

        return new Event(content, isDone, at);
    }

    /**
     * Serialize the Event instance to a string where the Event instance can be recreated from.
     *
     * @return the serialized Event instance
     */
    @Override
    public String getSerialized() {
        return String.format("E | %s | %s | %s", getIsDone(), getContent(), at);
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event other = (Event) o;
        return super.equals(other) && at.equals(other.at);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(at);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), getContent(), at);
    }
}
