public class Deadline extends Task {
    private final DateTime by;

    public Deadline(String content, String by) {
        super(content);
        this.by = new DateTime(by);
    }

    private Deadline(String content, DateTime dt) {
        super(content);
        this.by = dt;
    }

    public DateTime getBy() {
        return by;
    }

    public static Deadline deserialize(String str) {
        String[] words = str.split(" \\| ");
        boolean isDone = words[1].equals("1");
        String content = words[2];

        boolean isDateOnly = Boolean.parseBoolean(words[3]);
        String isoStr = words[4];

        Deadline deadline = new Deadline(content, DateTime.fromISODateTime(isoStr, isDateOnly));
        if (isDone) {
            deadline.markDone();
        }

        return deadline;
    }

    @Override
    public String getSerialized() {
        return String.format("D | %s | %s | %s | %s", getSerializedIsDone(), getContent(),
                by.getDateOnly(), by.toISODateTime());
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), getContent(), by);
    }
}
