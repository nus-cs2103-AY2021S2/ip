public class Task {
    String task;
    boolean done = false;

    Task(String task){
        this.task = task;
    }

    public void markDone(){
        this.done = true;
    }

    public boolean getStatus(){
        return this.done;
    }

    public String getTaskName(){
        return this.task;
    }

    @Override
    public String toString(){
        return this.task;
    }
}
