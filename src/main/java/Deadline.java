public class Deadline extends Task {
    private String date;

    public Deadline(String message, String date) {
        super("D", message);
        this.date = date;
    }

    @Override
    public String toMemString() {
        return super.toMemString() + " | " + this.date;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.date + ")";
    }
}
