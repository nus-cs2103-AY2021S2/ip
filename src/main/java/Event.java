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

    public String getData() {
        if (isDone == true) {
            return "E!@#1!@#" + taskInfo + "!@#" + dateInfo;
        } else {
            return "E!@#0!@#" + taskInfo + "!@#" + dateInfo;
        }
    }
}
