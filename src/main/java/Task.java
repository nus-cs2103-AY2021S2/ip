public class Task {
    private String description;
    private boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public boolean markAsDone() {
        if(!this.isDone) {
            this.isDone = true;
        }
        //Return boolean to signal that we have successfully / fail to mark task as done.
        return this.isDone;
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public String getStatusIcon() {
        return (this.isDone) ? "X" : " ";
    }
}
