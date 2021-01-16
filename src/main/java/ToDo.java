public class ToDo extends Task{
    ToDo(String name){
        super(name);
    }


    @Override
    public String toString(){
        return "[T]" + (this.done ? "[X]" : "[ ]") + this.getTaskName();
    }
}
