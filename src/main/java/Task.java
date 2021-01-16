public class Task {
    public  String taskName;
    public boolean done;
    public String type;

    Task(String name){
        this.taskName = name;
        this.done = false;
        this.type = "";
    }

    Task(String name, boolean done , String type){
        this.taskName = name;
        this.done = done;
        this.type = type;
    }

    public Task done() {
        this.done = true;
        return this;
    }

    @Override
    public String toString(){
        return done ?  " [X] " + this.taskName  : " [ ] " + this.taskName;
    }


}
