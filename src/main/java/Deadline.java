public class Deadline extends Task{
    String type;
    String time;
    Deadline(String job, String time) {
        super(job);
        this.type = "D";
        this.time = time;
    }
    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(by:" + this.time + ")";
    }
}
