public class Deadline extends Task {
    public String deadline;

    public Deadline(String str) {
        super(str);//super must be 1st line..
        String[] split = str.split("/");
        if(split.length < 2) {
            throw new IllegalArgumentException();
        }
        this.task = split[0].trim();
        this.deadline = split[1].trim();
    }

    public Deadline(String[] str, boolean isDone) {//call from harddisc
        super(str[2], isDone);
        this.deadline = str[3];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + deadline + ")";
    }
}
