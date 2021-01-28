//tasks that start at a specific time and ends at a specific time
public class Event extends Task{
    private String eventDuration;
    Event(String eventDuration, String eventDetail){
        super(eventDetail);
        this.eventDuration = eventDuration;
    }

    public String getEventDuration(){
        return this.eventDuration;
    }

    @Override
    public String toString(){
        return "[E]" +" | " + super.toString() + "| (at:" + this.eventDuration + ")";
    }
}
