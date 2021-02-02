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

    public Event(Boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    @Override
    public String toString() {
        String type = "[E]";
        String doneStatus = "[" + getStatusIcon() + "]";
        return type + doneStatus + " " + this.description + "(" + this.time + ")";
    }

    String time;

    /***
     * Format = {type}{done}{description}{deadline}
     */
    public String toStorage(){
        //type
        String res = "D";
        //done status
        res += "\u001E" + (isDone ? "T" : "F");
        //description
        res += "\u001E" + this.description;
        //deadline
        res += "\u001E" + this.time;
        return res;
    }
}
