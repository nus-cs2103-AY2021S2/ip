public class Event extends Task {
    protected String at;

    public Event(String des, String at) {
        super(des);
        this.at = at;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}
