package Task;

public class EventTask extends Task{
    private String type;
    private String time;

    public EventTask(String taskName, String time) {
        super(taskName);
        this.type = "[E]";
        this.time = time;
    }

    @Override
    public String toString() {
        return this.type + super.toString() + " (" + this.time + ")";
    }
}
