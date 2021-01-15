public class DeadlineTask extends Task {
    protected String deadline;

    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                done ? "X" : " ",
                name,
                deadline);
    }
}
