public class Task {
    protected String description, type;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public int getStatusNumber(){
        return (isDone) ? 1 : 0;
    }

    public String getType() {
        return type;
    }

    public String getTime(){
        return "";
    }

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}