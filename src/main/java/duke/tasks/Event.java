package duke.tasks;

public class Event extends Task{
    protected String after;
    public Event (String info, String after) {
        super(info, taskType.Event);
        this.after = after;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at:" + this.after + ")";
    }
}
