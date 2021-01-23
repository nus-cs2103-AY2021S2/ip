/**
 * Represents a task that is created from the user's command line input
 * @author Damith C. Rajapakse, Jeffry Lum
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "COMPLETED!" : "INPROGRESS");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
