package duke.util;

public class ToDo extends Task{
    private final String type;
    public ToDo(String job) {
        super(job);
        this.type = "T";
    }

    public ToDo(String job, Boolean isDone) {
        super(job, isDone);
        this.type = "T";
    }

    /**
     * Return a new ToDo object after Task is executed.
     */
    @Override
    public Task doTask() {
        return new ToDo(this.job, true);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString();
    }
}
