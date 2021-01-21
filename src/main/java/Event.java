public class Event extends Task{
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getType(){
        return "E";
    }

    @Override
    public String toString() {
        String type = "[E]";
        String doneStatus = "[" + getStatusIcon() + "]";
        return type + doneStatus + " " + this.description + "(" + this.time + ")";
    }

    String time;
}
