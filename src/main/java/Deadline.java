public class Deadline extends Task{
    String dateTime;

    public Deadline(boolean markAsDone, String taskName, String dateTime) {
        super('D', markAsDone, taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String generateFileFormatString() {
        return super.generateFileFormatString() + " // " + this.dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dateTime);
    }
}
