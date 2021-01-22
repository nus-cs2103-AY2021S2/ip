public class DeadlineTask extends Task {
    protected String date;

    public DeadlineTask(String description, String date) {
        super(description, 'D');
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
