public abstract class Task {
    private final String description;
    protected boolean isDone;
    private static int counter = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "*" : " "); //Don't use unicode, cause it can't test properly
    }

    @Override
    public String toString() {
        return "["+ this.getStatusIcon()+"]: " +
                description;
    }

    //...
}