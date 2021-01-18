public class Event extends Task{
    private String deadline;

    public Event(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (at: %s)", super.toString(), this.deadline);
    }
}
