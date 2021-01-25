public class Event extends Task {

    protected String at;
    protected static String type = "E";

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

//    public String archiveEvent() {
//        String s = type + " | " + (this.isDone ? "1" : "0") + " | " + this.description;
//        return s;
//    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
