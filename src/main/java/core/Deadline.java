package core;

public class Deadline extends Task {
    private String byTime;

    public Deadline(String desc) {
        super(desc);

        var parts = desc.split("/by");
        this.taskDescription = parts[0].trim();
        this.byTime = parts[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + byTime + ")";
    }
}
