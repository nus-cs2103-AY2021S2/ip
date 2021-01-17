public class EventTask extends Task {
    String at;
    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.at + ")";
    }
}
