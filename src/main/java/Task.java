public abstract class Task {
    private String taskName;
    protected boolean done;

    Task(String name){
        this.done = false;
        this.taskName = name;
    }

    public void markAsDone(){
        this.done = true;
    }

    public String getTaskName(){
        return this.taskName;
    }
}
