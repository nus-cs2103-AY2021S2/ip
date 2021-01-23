public class Event extends Task{



    public Event(String description, String eventDate) {
       super(description);
        this.date = eventDate;
        this.type = TaskEnum.E;
    }

    @Override
    public String getDescription() {
        return this.description + " (at: " + this.date + ")";
    }

}
