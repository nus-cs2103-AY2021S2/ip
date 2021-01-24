public class DeadlineTask extends Task {
    private String date;
    private static final String SEPARATOR = "|";

    public DeadlineTask(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    public String getSavingString() {
        return "DEADLINE" + super.getSavingString() + SEPARATOR + date + "\n";
    }
}
