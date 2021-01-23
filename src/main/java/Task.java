//Below solution adapted from partial solution in: https://nus-cs2103-ay2021s2.github.io/website/schedule/week2/project.html
public abstract class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    public Task(String description, String type, boolean isDone) {
        this.description = description;
        this.type = type;
        this.isDone = isDone;
    }

    public String getType() {
        return this.type;
    }

    public int getStatusInt() {
        if (this.isDone) {
            return 1;
        } else {
            return 0;
        }
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
    
    public String saveTask() {
        return String.format("%s/split/%s/split/%s", this.getType(), this.getStatusInt(), this.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getType(), this.getStatusIcon(), this.getDescription());
    }
}