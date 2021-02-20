public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public static Deadline getDeadline(String description, String by) {
        return new Deadline(description, by, false);
    }

    @Override
    public Task markDone(){
        return new Deadline(description,by,true);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

