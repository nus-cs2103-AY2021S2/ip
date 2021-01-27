import static java.lang.Boolean.parseBoolean;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = ParseDateTime.parse(deadline);
    }

    private Deadline(String desc, String deadline, boolean isDone) {
        super(desc, isDone);
        this.deadline = ParseDateTime.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description
                + " (by: " + ParseDateTime.readableString(deadline) + ")";
    }

    @Override
    public String unparse() {
        return "D" + delimiter + description + delimiter + isDone
                + delimiter + ParseDateTime.unparse(deadline) + System.lineSeparator();
    }

    // note that this parsing is different from parsing user inputs.
    // since this parsing for hard disk storage is separate from that parsing,
    // the /at /by style of inputs won't affect this if they change, i think
    public static Deadline parse(String oneLine) {
        // some repetition in this function across all types of tasks but abstracting them might be costly
        assert oneLine.startsWith("T" + delimiter);

        int descStartIdx = oneLine.indexOf(delimiter);
        int descEndIdx = oneLine.indexOf(delimiter, descStartIdx + 1);
        String desc = oneLine.substring(descStartIdx + delimiter.length(), descEndIdx);

        int doneEndIdx = oneLine.indexOf(delimiter, descEndIdx + 1);
        String doneStr = oneLine.substring(descEndIdx + delimiter.length(), doneEndIdx);
        boolean isDone = parseBoolean(doneStr);


        String deadline = oneLine.substring(doneEndIdx + delimiter.length());

        return new Deadline(desc, deadline, isDone);
    }

    // for testing purposes
    public static void main(String[] args) {
        Deadline t = new Deadline("hello world", "7am on wed");
        System.out.println(t);
        t.markAsDone();
        System.out.println(t);
        System.out.println(t.unparse());
        System.out.println(parse(t.unparse()));
        // test with other cases without relying on t
    }


}
