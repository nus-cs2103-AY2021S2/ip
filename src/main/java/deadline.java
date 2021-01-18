public class deadline extends Task {
    protected String description;
    protected boolean done;
    protected String date;

    deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By:" + date + ")";
    }
}
