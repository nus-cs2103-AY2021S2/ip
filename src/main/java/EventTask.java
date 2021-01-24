public class EventTask extends Task {

    private String duration;

    public EventTask(String description, int id) {
        super(description, id);
        this.duration = description.split("/at")[1];
        this.description = description.split("/at")[0];
    }

    public EventTask(String description, int id, int status, String duration) {
        super(description, id);
        super.isDone = status > 0;
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.checkBoxToString() + description + "(at:" + duration + ")";
    }
}
