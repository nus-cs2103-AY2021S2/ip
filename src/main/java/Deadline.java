public class Deadline extends Task {
    private String time;

    Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getTime());
    }
}
