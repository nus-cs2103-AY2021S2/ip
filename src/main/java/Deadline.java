public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String by) {
        super(description);
        this.date = by;
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + date;
    }
}
