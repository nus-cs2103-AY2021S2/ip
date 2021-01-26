public class Task {
    private String description;
    private boolean isDone;
    private String type;
    protected String separator = " | ";

    public Task(String description) {
        this(description, false);
//        this.description = description;
//        this.isDone = false;
//        this.type = "";
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
//        this.type = "";
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

//    public void setSatus(boolean isDone){
//        this.isDone = isDone;
//    }

    public boolean getStatus() { return this.isDone; }

    @Override
    public String toString() {
        return this.getStatusIcon() + separator + this.getDescription();
    }
}