public class todo extends listItem{
    public todo(String task){
        super(task);
    }

    @Override
    public String toString(){
        return "[T]" + (super.isDoneStatus() == true ? "[X] " : "[ ] ") + super.getTask();
    }
}
