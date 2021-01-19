public class Deadline extends Task {
    private String endDate;

    public Deadline(String desc, String endDate) {
        super(desc);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + endDate + ")";
    }
}
