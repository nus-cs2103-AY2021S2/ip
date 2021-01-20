public class Events extends Task{
    private String eventPeriod;

    public Events(String description, String eventPeriod) {
        super(description);
        this.eventPeriod = eventPeriod;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (Event Time: " + eventPeriod + ")";
    }
}
