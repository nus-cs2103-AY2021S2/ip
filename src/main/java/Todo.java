import static java.lang.Boolean.parseBoolean;

/**
 * This class implements a type of task that users can add to their tasklist on this app.
 * The task has no variable specific to it that is of interest to the user (unlike event
 * that has an eventTiming variable).
 */
public class Todo extends Task {
    private static final String taskType = "T";

    public Todo(String desc) {
        super(desc);
    }

    // only used by parsing function
    private Todo(String desc, Boolean isDone) {
        super(desc, isDone);
    }

    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    @Override
    public String unparse() {
        return "T" + delimiter + description + delimiter + isDone + System.lineSeparator();
    }


    /**
     * Creates a Todo object based on the string stored in the hard disk.
     * @param oneLine One line of stored input to be parsed into a todo
     * @return Todo Object
     */
    public static Todo parse(String oneLine) {
        assert oneLine.startsWith("T" + delimiter);

        int descStartIdx = oneLine.indexOf(delimiter);
        int descEndIdx = oneLine.indexOf(delimiter, descStartIdx + 1);
        String desc = oneLine.substring(descStartIdx + delimiter.length(), descEndIdx);

        String doneStr = oneLine.substring(descEndIdx + delimiter.length());
        boolean isDone = parseBoolean(doneStr);

        return new Todo(desc, isDone);
    }

    // for testing purposes
    public static void main(String[] args) {
    }

}
