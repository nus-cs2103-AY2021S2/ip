public class Deadline extends Task{
    String dateTime;

    public Deadline(String taskName, String dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dateTime);
    }
}
