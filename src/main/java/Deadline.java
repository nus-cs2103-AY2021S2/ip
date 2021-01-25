public class Deadline extends Task {
    private final DateTime by;

    public Deadline(String content, String by) {
        super(content);
        this.by = new DateTime(by);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), getContent(), by);
    }
}
