public class Event extends Task {
    private String timestamp;

    public Event(String n, String t) {
        isDone = false;
        name = n;
        timestamp = t;
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
