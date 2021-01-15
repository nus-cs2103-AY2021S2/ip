public class ToDo extends Task{

    ToDo(String taskName) {
        super(taskName);
    }

    ToDo(String taskName, boolean done) {
        super(taskName, done);
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
