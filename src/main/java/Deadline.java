public class Deadline extends Task{
    protected String by;

    public Deadline(int number, String name, String by) {
        super(number, name);
        this.by = by;
    }

    @Override
    public void addTask(int count) {
        super.addTask(count);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}

