public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public String getDescription(){
        return this.description;
    }

    public Task markAsDone(){
        String description = this.getDescription();

        return new Task(description,true);
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    public String toString(){
        return "[" + this.getStatusIcon() + "]" + this.getDescription();
    }

}
