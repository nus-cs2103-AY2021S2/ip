public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        //return tick or cross symbols
        return (isDone ? "\u2713" : " ");
    }

    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
