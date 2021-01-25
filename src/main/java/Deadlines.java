public class Deadlines extends Task {
    String deadlineTime;

    public Deadlines(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + "(by: " + this.deadlineTime + ")";
    }

    @Override
    public String saveStatus() {
        return "D" + super.saveStatus();
    }
}
