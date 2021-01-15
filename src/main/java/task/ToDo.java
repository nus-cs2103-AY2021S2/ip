package task;

public class ToDo extends Task{

    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(String taskName, boolean done) {
        super(taskName, done);
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
