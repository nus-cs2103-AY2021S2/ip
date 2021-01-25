public class todo extends Task {
    todo(String description) {
        super(description);
    }

    @Override
    public String get_date() { return "error"; }
    @Override
    public String get_initial() { return "T"; }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
