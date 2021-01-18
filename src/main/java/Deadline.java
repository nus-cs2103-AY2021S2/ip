public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    protected Deadline(String name, String dueDate, boolean completed) {
        super(name, completed);
        this.dueDate = dueDate;
    }

    @Override
    public Task completeTask() {
        return new Deadline(this.name, this.dueDate, true);
    }

    @Override
    public String toString() {
        return super.toString().replace("[T]", "[D]") + " (by: " + this.dueDate + ")";
    }

}