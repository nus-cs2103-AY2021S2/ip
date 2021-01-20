public class Task {
    protected String description;
    protected boolean isDone;
    protected int number;

    public Task(String description, int number) {
        this.description = description;
        this.isDone = false;
        this.number = number;
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