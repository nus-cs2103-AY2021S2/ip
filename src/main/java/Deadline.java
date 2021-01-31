public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toSaveFormat() {
        String status = super.isDone ? "1" : "0";
        return String.format("D|%s|%s\n", status, super.description, this.time);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.time);
    }
}
