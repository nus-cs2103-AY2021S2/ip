package duke.task;

import duke.util.DateTime;

public class Deadline extends Task {
    private final DateTime by;

    public Deadline(String content, String by) {
        super(content);
        this.by = new DateTime(by);
    }

    private Deadline(String content, boolean isDone, DateTime dt) {
        super(content, isDone);
        this.by = dt;
    }

    public DateTime getBy() {
        return by;
    }

    public static Deadline deserialize(String str) {
        String[] words = str.split(" \\| ");
        boolean isDone = Boolean.parseBoolean(words[1]);
        String content = words[2];

        boolean isDateOnly = Boolean.parseBoolean(words[3]);
        String isoStr = words[4];

        return new Deadline(content, isDone, DateTime.fromISODateTime(isoStr, isDateOnly));
    }

    @Override
    public String getSerialized() {
        return String.format("D | %s | %s | %s | %s", getIsDone(), getContent(),
                by.getDateOnly(), by.toISODateTime());
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), getContent(), by);
    }
}
