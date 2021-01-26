public class Deadline extends Task {
    private String date;

    public Deadline(String input, String date) {
        super(input);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
