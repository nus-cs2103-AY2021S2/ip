public class deadline extends Task {
    protected String date;

    deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String get_date() { return date; }

    @Override
    public String get_initial() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By:" + date + ")";
    }
}
