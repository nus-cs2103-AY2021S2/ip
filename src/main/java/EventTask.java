public class EventTask extends Task {
    private String date;
    private static final String SEPARATOR = "|";

    public EventTask(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }

    public String getSavingString() {
        return "EVENT" + super.getSavingString() + SEPARATOR + date + "\n";
    }
}
