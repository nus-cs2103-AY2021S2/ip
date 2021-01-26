public class Deadline extends Task {
    private String timestamp;

    public Deadline(String n, String t) {
        isDone = false;
        name = n;
        timestamp = t;
    }

    public String toText() {
        String d = isDone ? "+" : "-";
        return String.format("D | %1$s | %2$s | %3$s", d, name, timestamp);
    }

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]")
                + " Deadline: "
                + name
                + " by "
                + timestamp;
    }
}
