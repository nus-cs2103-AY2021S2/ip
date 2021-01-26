public class Deadline extends Task{
    protected String by;
    Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    Deadline(boolean done,String task, String by) {
        super(task);
        this.done = done;
        this.by = by;
    }

    String fileString() {
        return "D | " + this.done + " | " + this.task + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + this.by + ")";
    }
}
