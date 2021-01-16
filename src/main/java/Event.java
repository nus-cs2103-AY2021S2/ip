public class Event extends Task{
    private String eventDate;


    public Event(String description, String eventDate) {
       super(description);
       this.eventDate = eventDate;
        this.type = TaskEnum.E;
    }

    @Override
    public String getDescription() {
        return this.description + " (at: " + this.eventDate + ")";
    }

}
