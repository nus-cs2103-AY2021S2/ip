public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false; //task not done is represented by a X
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols.
    }

    public String getDescription() {

        return this.description;
    }

    public void markAsDone() {
        this.isDone = true; //a task that is done is represented by a tick
    }

    public String getType() {
        return "[ ]";
    }


    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

}
