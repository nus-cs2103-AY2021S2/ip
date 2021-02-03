public class Task {
    public boolean isDone;
    public String name;

    // constructor
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task setDone() {
        Task doneTask = new Task(this.name);
        doneTask.isDone = true;
        return doneTask;
    }

    public String getSaveText() {
        StringBuilder sb = new StringBuilder();
        if (this.isDone) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(this.name);
        return sb.toString();
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
