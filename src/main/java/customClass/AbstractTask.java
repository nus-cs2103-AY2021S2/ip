package customClass;

public abstract class AbstractTask {
    protected String description;
    protected boolean isDone;

    public AbstractTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    public AbstractTask(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract void execute();
}
