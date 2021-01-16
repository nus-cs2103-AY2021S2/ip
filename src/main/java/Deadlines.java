public class Deadlines extends Task{
    public String by;
    Deadlines(String name, String by){
        super(name);
        this.by = by;
    }

    Deadlines(String name, boolean done, String by){
        super(name, done, "[D]");
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
