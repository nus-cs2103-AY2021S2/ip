public class Deadline extends Task {
    protected String period;

    public Deadline(String description, String period) {
        super(description);
        this.period = period;
    }

    @Override
    public String saveFormat() {
        return "D | " + super.saveFormat() + " | " + period;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + period + ")";
    }
}
