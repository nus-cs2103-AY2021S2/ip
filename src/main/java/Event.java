public class Event extends Task{
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    String startTime;
    String endTime;
}
