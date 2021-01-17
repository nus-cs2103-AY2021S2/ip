public class Deadline extends Task {
    private String timestamp;

    public Deadline(String n, String t) {
        isDone = false;
        name = n;
        timestamp = t;
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
