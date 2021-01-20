public abstract class Task {
    private final String description;
    protected boolean isDone;
    private static int counter = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "["+ this.getStatusIcon()+"]: " +
                description;
    }

    //...
}