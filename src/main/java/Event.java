public class Event extends Task {
    private String dateInfo;

    public Event(String taskInfo, String dateInfo) {
        super(taskInfo);
        this.dateInfo = dateInfo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + dateInfo + ")";
    }
}
