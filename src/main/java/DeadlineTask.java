public class DeadlineTask extends Task {
    private final String deadline;

    public DeadlineTask(String desc, String date) {
        super(desc);
        this.deadline = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
