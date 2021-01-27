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
        int start = 0;
        assert oneLine.startsWith("T" + delimiter);

        int descStartIdx = oneLine.indexOf(delimiter);
        int descEndIdx = oneLine.indexOf(delimiter, descStartIdx + 1);
        String desc = oneLine.substring(descStartIdx + delimiter.length(), descEndIdx);

        String doneStr = oneLine.substring(descEndIdx + delimiter.length());
        Boolean isDone = parseBoolean(doneStr);

//        System.out.println("task description");
//        System.out.println(desc);
//        System.out.println("bool");
//        System.out.println(isDone);
//        System.out.println(doneStr);

        return new Todo(desc, isDone);
    }

    // for testing purposes
    public static void main(String[] args) {
//        Todo t = new Todo("hello world");
//        System.out.println(t);
//        //t.markAsDone();
//        System.out.println(t);
//        System.out.println(t.unparse());
//        System.out.println(parse(t.unparse()));
//        // test with other cases without relying on t
    }

}
