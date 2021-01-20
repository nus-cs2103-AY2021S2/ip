public class EventTask extends Task {

    private String duration;

    public EventTask(String description, int id) {
        super(description, id);
        this.duration = description.split("/at")[1];
        this.description = description.split("/at")[0];
    }
    
    @Override public String toString() {
        return super.toString() + " [E]" + super.checkBoxToString()
        + description + "(at:" + duration + ")";
    }
}
