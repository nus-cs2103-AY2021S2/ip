public class Deadlines extends Task{
    public String by;
    Deadlines(String name, String by){
        super(name);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + (this.done ? "[X] " : "[ ]") + this.getTaskName() +  " (by: " + this.by + ")";
    }
}
