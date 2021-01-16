public class Events extends Task{
    protected String by;
    Events(String name, String by){
        super(name);
        this.by = by;
    }


    @Override
    public String toString(){
        return "[E]" + (this.done ? "[X] " : "[ ]") + this.getTaskName() +  " (by: " + this.by + ")";
    }
}
