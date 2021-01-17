public class todo extends listItem{
    public todo(String task){
        super(task);
    }

    public todo(String task, boolean isDone){
        super(task, isDone);
    }

    @Override
    public listItem markAsDone(){
        return new todo(super.getTask(), true);
    }

    @Override
    public String toString(){
        return "[T]" + (super.isDoneStatus() == true ? "[X] " : "[ ] ") + super.getTask();
    }
}
