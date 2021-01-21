public class Task {
    protected String taskName;
    protected boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    public String getStatus(){
        return completed ? "\u2717" : " ";
    }

    public void setCompleted(){
        this.completed = true;
    }

    @Override
    public String toString() {
        return this.taskName;
        //return "["+this.getStatus()+"] " + this.taskName;
    }
}
