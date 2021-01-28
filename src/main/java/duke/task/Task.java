package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int status;

    public Task(String description, int status) {
        this.description = description;
        this.status = status;
        if(status == 1) {
            this.isDone = true;
        }else{
            this.isDone = false;
        }
    }

    public String getDescription(){
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
        this.status = 1;
    }

    public String toTxt(){
        return String.format("| %s | %s", isDone ? "1" : "0", this.description);
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description ;
    }

}
