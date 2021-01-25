public class Event extends Task{
    protected String time;

    Event(String description, boolean isCompleted, String time) {
        super(description, isCompleted);
        this.time = time;
    }

    @Override
    public String getFormattedData() {
        return  "E | " + super.getFormattedData() + "| " + time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}
