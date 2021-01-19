public class Event extends Task {

    private String timeslot;

    public Event(String description, String timeslot){
        super(description);
        this.timeslot = timeslot;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + timeslot + ")";
    }
}