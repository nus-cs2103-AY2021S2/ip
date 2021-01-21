public class Task {
    public String description;
    public boolean isDone;
    public static int count = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
