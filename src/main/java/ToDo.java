public class ToDo extends Task{
    ToDo(String name){
        super(name);
    }

    @Override
    public String toSaveFormat(){
        return "T | " + (isDone() ? "1" : "0") +
                " | " + this.getTaskName();
    }


    @Override
    public String toString(){
        return "[T]" + (this.done ? "[X] " : "[ ] ") + this.getTaskName();
    }
}
