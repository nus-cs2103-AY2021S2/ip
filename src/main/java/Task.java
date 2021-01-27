public class Task {
    private boolean isDone;
    private String taskDetail;
    Task(String taskDetail){
        this.taskDetail = taskDetail;
        this.isDone = false;
    }

    public boolean getTaskStatus(){
        return this.isDone;
    }

    public String getTaskDetail(){
        return this.taskDetail;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] | " + this.getTaskDetail();
    }

}
