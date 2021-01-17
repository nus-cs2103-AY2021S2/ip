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
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new UnsupportedOperationException("This task is already done.\n" +
                    "I would have wanted to say Stonks...\n" +
                    "but your usage of an illegal operation is Not Stonks!");
        }
    }

    public void markAsUndone() {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new UnsupportedOperationException("This task is already not done. Not stonks anyway!");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}