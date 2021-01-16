public class Task {
    public final String taskName;
    public final boolean done;

    Task(String name){
        this.taskName = name;
        this.done = false;
    }

    Task(String name, boolean done){
        this.done = done;
        this.taskName = name;
    }

    public Task done(){
        return  new Task(this.taskName, true);
    }

    @Override
    public String toString(){
        return done ? "[X] " + this.taskName : "[ ] " + this.taskName;
    }


}
