import static java.lang.Boolean.parseBoolean;
import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class    Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Creates a deadline object
     * @param desc description of the deadline object
     * @param deadline date/time that the deadline task is due
     */
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

    // since this parsing is separate from the parsing in the duke function
    // the /at /by style of inputs won't affect this if they change, i think

    //E;;desc;;true;;timing

    /**
     * Creates a deadline object based on user input.
     * @param oneLine One line of user input to be parsed into a deadline
     * @return Deadline Object
     */
    public static Deadline parse(String oneLine) {
        // some repetition in this function across all types of tasks but abstracting them might be costly
        int start = 0;
        assert oneLine.startsWith("T" + delimiter);

        int descStartIdx = oneLine.indexOf(delimiter);
        int descEndIdx = oneLine.indexOf(delimiter, descStartIdx + 1);
        String desc = oneLine.substring(descStartIdx + delimiter.length(), descEndIdx);

        int doneEndIdx = oneLine.indexOf(delimiter, descEndIdx + 1);
        String doneStr = oneLine.substring(descEndIdx + delimiter.length(), doneEndIdx);
        Boolean isDone = parseBoolean(doneStr);


        String deadline = oneLine.substring(doneEndIdx + delimiter.length());

        return new Deadline(desc,  deadline, isDone);
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
