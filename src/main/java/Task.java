//Below solution adapted from partial solution in: https://nus-cs2103-ay2021s2.github.io/website/schedule/week2/project.html
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        if (this.isDone) {
            return; //To be replaced with thrown exception
        } else {
            this.isDone = true;
        }
    }

    public void markAsUndone() {
        if (!this.isDone) {
            return; //To be replaced with thrown exception
        } else {
            this.isDone = false;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}