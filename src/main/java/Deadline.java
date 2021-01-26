public class Deadline extends Task{
    private final String type;
    private final String time;
    Deadline(String job, String time) {
        super(job);
        this.type = "D";
        this.time = time;
    }

    Deadline(String job, Boolean done, String time) {
        super(job, done);
        this.type = "D";
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public Task doTask() {
        return new Deadline(this.job, true, this.time);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(by:" + this.time + ")";
    }
}
