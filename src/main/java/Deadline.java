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

    public String getData() {
        if (isDone == true) {
            return "D!@#1!@#" + taskInfo + "!@#" + dateInfo;
        } else {
            return "D!@#0!@#" + taskInfo + "!@#" + dateInfo;
        }
    }
}
