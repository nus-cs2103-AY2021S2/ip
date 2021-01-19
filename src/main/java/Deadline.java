public class Deadline extends Task{
    private String dueDates;
    public Deadline(String task, String dueDates) {
        super(task);
        this.dueDates = dueDates;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dueDates + ")";
    }
}
