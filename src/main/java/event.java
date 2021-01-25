public class event extends Task {
    protected String date;

    event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String get_date() { return date; }

    @Override
    public String get_initial() { return "E"; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
