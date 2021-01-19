public class Event extends Task{
    protected String at;
    public Event(String description,String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskInfo() {
        return "[E]" + super.getTaskInfo() + " (at: " + at + ")";
    }
}
