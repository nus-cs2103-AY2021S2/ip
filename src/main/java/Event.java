public class Event extends Task {
    private String timestamp;

    public Event(String n, String t) {
        isDone = false;
        name = n;
        timestamp = t;
    }

    public String toText() {
        String d = isDone ? "+" : "-";
        return String.format("E | %1s | %2s | %3s", d, name, timestamp);
    }

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]")
                + " Event: "
                + name
                + " by "
                + timestamp;
    }
}
