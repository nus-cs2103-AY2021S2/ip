public class Deadline extends Task {
    private final String by;

    public Deadline(String content, String by) {
        super(content);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), getContent(), by);
    }
}
