public class Deadline extends Task {
    private final String time;

    Deadline(String name, TaskType type, String time) {
        super(name, type);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + time + ")";
    }

}
