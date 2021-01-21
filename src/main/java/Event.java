public class Event extends Task{
    /**
     * Returns an Event
     * @param description description of the event
     * @param time , which is currently still in String form but I suspect that might change
     * **/
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        String type = "[E]";
        String doneStatus = "[" + getStatusIcon() + "]";
        return type + doneStatus + " " + this.description + "(" + this.time + ")";
    }

    String time;
}
