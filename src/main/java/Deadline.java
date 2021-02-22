public class Deadline extends Task{

    protected TimeWrapper by;

    public Deadline(String description, TimeWrapper by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public static Deadline getDeadline(String description, String by) {
        return new Deadline(description, TimeWrapper.getTimeWrapper(by), false);
    }

    @Override
    public String getContentString(){
        return String.format("D|%d|%S|%S",isDone ? 1 : 0, this.description, this.by);

    }

    @Override
    public Task markDone(){
        return new Deadline(description,by,true);
    }

    @Override
    public boolean isSameTime(String time) {
        return by.equals(TimeWrapper.getTimeWrapper(time));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

