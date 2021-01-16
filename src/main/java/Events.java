public class Events extends Task{
    protected String by;
    Events(String name, String by){
        super(name);
        this.by = by;
    }

    Events(String name, boolean done, String by){

        super(name, done, "[E]");
        this.by = by;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (by: " + this.by + ")";
    }
}
