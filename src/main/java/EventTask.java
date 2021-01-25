import java.time.LocalDate;

public class EventTask extends Task {

    private LocalDate eventDate;
    private String startTime;
    private String endTime;

    public EventTask(String description, int id, int status, LocalDate duration, String startTime, String endTime) {
        super(description, id);
        super.isDone = status > 0;
        this.eventDate = duration;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String serializeEvent() {
        return eventDate.toString() + " | " + startTime + " | " + endTime;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.checkBoxToString() + description + " (at: "
                + eventDate + " " + startTime + " to " + endTime + " HRS" + ")";
    }
}
