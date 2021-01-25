public class Deadline extends Task {
    private final String date;

    Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

    public String getDate() {
        return this.date;
    }
}
