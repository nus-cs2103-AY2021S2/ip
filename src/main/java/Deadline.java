public class Deadline extends Task {
    String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    public Deadline(String name, boolean isDone, String time) {
        super(name, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), time);
    }
}
