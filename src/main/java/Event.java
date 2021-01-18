public class Event extends Task{
    String type;
    String time;
    Event(String job, String time) {
        super(job);
        this.type = "E";
        this.time = time;
    }
    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(at:" + this.time + ")";
    }
}
