import static java.lang.Boolean.parseBoolean;

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

    //T;;desc;;true
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
