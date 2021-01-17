
public class DeadlineTask extends Task {

    private String deadline;

    public DeadlineTask(String info, String deadline) {
        super(info);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
