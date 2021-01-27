public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    // public String getStatusIcon() {
    // return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    // }
    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    public String savedFormat() {
        String savedInfo;
        if (this.isDone()) {
            savedInfo = "T | 1 | " + this.getDescription(); 
        } else {
            savedInfo = "T | 0 | " + this.getDescription(); 
        }
        return savedInfo;
    }
}