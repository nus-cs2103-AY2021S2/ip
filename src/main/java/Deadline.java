public class Deadline extends Task{
    protected String deadline;

    Deadline(String description, boolean isCompleted, String deadline) {
        super(description, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public String getFormattedData() {
        return  "D | " + super.getFormattedData() + "| " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }
}
