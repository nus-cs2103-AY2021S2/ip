public class Deadline extends Task {
    public String deadline;

    public Deadline(String str) {
        super(str);//super must be 1st line..
        String[] split = str.split("/");
        this.task = split[0].trim();
        this.deadline = split[1].trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + deadline + ")";
    }
}
