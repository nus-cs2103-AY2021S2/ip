public class Deadline extends Task {

    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public Task setDone() {
        Deadline doneTask = new Deadline(this.name, this.by);
        doneTask.isDone = true;
        return doneTask;
    }

    @Override
    public String getSaveText() {
        return "D | " + super.getSaveText();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

}
