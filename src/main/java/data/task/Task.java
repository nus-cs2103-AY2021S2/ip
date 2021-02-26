package data.task;

public class Task implements ITask {

    protected String description;
    protected boolean isDone;


    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public static Task getTask(String description){
        return new Task(description, false);
    }

    @Override
    public boolean isSameTime(String time) {
        return false;
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public ITask markDone(){
        return new Task(this.description,true);
    }

    @Override
    public String getContentString(){
        return String.format("TASK|%d|%s", this.isDone() ? 1 : 0, this.description);
    }
    
    @Override
    public String toString(){
        return "[" + (isDone ? "V" : "X") + "]" + this.description;
    }

}
