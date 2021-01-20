public class Task {
    private final String description;
    protected boolean isDone;
    private final int number;
    private static int counter = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.number = Task.counter++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "Entry " +
                (number+1) +
               "|["+ this.getStatusIcon()+"]: " +
                description;
    }

    //...
}