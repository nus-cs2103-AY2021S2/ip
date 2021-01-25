public class Event extends Task {
    protected String at;

    public Event(String description, TaskType taskType, String at) {
        super(description, taskType);
        this.at = at;
    }
    
    @Override
    public String saveTaskString() {
        String delimiter = " ~ ";
        return super.saveTaskString() + delimiter + this.at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.at + ")";
    }
}
