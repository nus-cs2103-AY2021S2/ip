public class Deadline extends Task {
    private String time;

    public Deadline(String taskDescription, String time) {
        super(taskDescription);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D][" + (done ? "X" : " ") + "] " + taskDescription + " (" + time + ")";
    }
}
