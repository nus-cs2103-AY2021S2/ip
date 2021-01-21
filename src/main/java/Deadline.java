public class Deadline extends Task {
    private String dateInfo;

    public Deadline(String taskInfo, String dateInfo) {
        super(taskInfo);
        this.dateInfo = dateInfo;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + dateInfo + ")";
    }
}
