public class Event extends Task{
    private final String type;
    private final String time;
    Event(String job, String time) {
        super(job);
        this.type = "E";
        this.time = time;
    }

    Event(String job, Boolean done, String time) {
        super(job, done);
        this.type = "E";
        this.time = time;
    }

    @Override
    public Event doTask() {
        return new Event(this.job, true, this.time);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(at:" + this.time + ")";
    }
}
