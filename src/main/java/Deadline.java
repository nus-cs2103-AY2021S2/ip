public class Deadline extends Task {
    protected boolean isDone;
    protected String time;
    protected final static String type = "[D]";

    public Deadline(String description, String time) {
        super(description);
        this.isDone = false;
        this.time = time.substring(2);
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String getType() {
        return type;
    }

}
